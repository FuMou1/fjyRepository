<template>
	<view class="content b-t">
		<view class="list b-b" v-for="(item, index) in addressList" :key="index" @click="checkAddress(item)">
			<view class="wrapper">
				<view class="address-box">
					<text v-if="item.isDefault" class="tag">默认</text>
					<text class="address">{{item.province}} {{item.city}} {{item.county}} {{item.town}}
						{{item.address}}</text>
				</view>
				<view class="u-box">
					<text class="name">{{item.name}}</text>
					<text class="mobile">{{item.phone}}</text>
				</view>
			</view>
			<text class="yticon icon-bianji" @click.stop="addAddress('edit', item)"></text>
			<text class="del-btn yticon icon-fork" @click="deleteAddressItem(item.id)"></text>
		</view>
		<text style="display:block;padding: 16upx 30upx 10upx;lihe-height: 1.6;color: #fa436a;font-size: 24upx;">
			重要：添加和修改地址回调仅增加了一条数据做演示，实际开发中将回调改为请求后端接口刷新一下列表即可
		</text>

		<button class="add-btn" @click="addAddress('add',{userId:source})">新增地址</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				source: 0,
				addressList: [{
					name: '刘晓晓',
					mobile: '18666666666',
					addressName: '贵族皇仕牛排(东城店)',
					address: '北京市东城区',
					area: 'B区',
					default: true
				}, {
					name: '刘大大',
					mobile: '18667766666',
					addressName: '龙回1区12号楼',
					address: '山东省济南市历城区',
					area: '西单元302',
					default: false,
				}]
			}
		},
		onLoad(option) {
			console.log(option.userId);
			this.source = option.userId;
			let params = {
				"userId": option.userId, //用户id
				"limit": 10,
				"page": 1
			}

			uni.request({

				url: `${this.$baseUrl}/app/api/deliveryaddress/listByPage`,

				method: 'POST',

				data: params,

				success: (res) => {
					console.log("成功");
					console.log(res);
					var defaultAddresss = res.data.data.records;
					this.addressList = defaultAddresss;

				},

				fail: (err) => {
					console.log("失败");
					console.log(res)
				}

			})
		},
		methods: {
			//选择地址
			checkAddress(item) {
				if (this.source == 1) {
					//this.$api.prePage()获取上一页实例，在App.vue定义
					this.$api.prePage().addressData = item;
					uni.navigateBack()
				}
			},
			//新增或修改地址
			addAddress(type, item) {
				uni.navigateTo({
					url: `/pages/address/addressManage?type=${type}&data=${JSON.stringify(item)}`
				})
			},
			//删除地址
			deleteAddressItem(id) {
				uni.showModal({
					content: '确认删除该收货地址？',
					success: (e)=>{
						if(e.confirm){
							var ids = [];
							ids.push(id);
							
							uni.request({
							
								url: `${this.$baseUrl}/app/api/deliveryaddress/delete`,
							
								method: 'DELETE',
							
								data: JSON.stringify(ids),
							
								success: (res) => {
									console.log(res);
									if (res.data.code == 0) {
										console.log("删除用户收货地址成功");
										//删除列表中的数据
										this.addressList.some((item, i) => {
											if (item.id == id) {
												this.addressList.splice(i, 1)
												//在数组的some方法中，若是return true，就会当即终止这个数组的后续循环
												return true
											}
										})
									} else {
										this.$api.msg("删除用户收货地址失败:" + res.data.msg);
									}
								},
								fail: (err) => {
									console.log("删除用户收货地址失败");
									console.log(err)
								}
							})
						}
					}
				})
			},
			//添加或修改成功之后回调
			refreshList(data, type) {
				console.log(data, type);
				//添加或修改后事件，这里直接在最前面添加了一条数据，实际应用中直接刷新地址列表即可
				if(type=="edit"){
					//删除列表中的数据
					this.addressList.some((item, i) => {
						if (item.id == data.id) {
							this.addressList.splice(i, 1,data)
							//在数组的some方法中，若是return true，就会当即终止这个数组的后续循环
							return true
						}
					})
				}else{
					this.addressList.unshift(data);
				}
			}
		}
	}
</script>

<style lang='scss'>
	page {
		padding-bottom: 120upx;
	}

	.content {
		position: relative;
	}

	.list {
		display: flex;
		align-items: center;
		padding: 20upx 30upx;
		;
		background: #fff;
		position: relative;
	}

	.wrapper {
		display: flex;
		flex-direction: column;
		flex: 1;
	}

	.address-box {
		display: flex;
		align-items: center;

		.tag {
			font-size: 24upx;
			color: $base-color;
			margin-right: 10upx;
			background: #fffafb;
			border: 1px solid #ffb4c7;
			border-radius: 4upx;
			padding: 4upx 10upx;
			line-height: 1;
		}

		.address {
			font-size: 30upx;
			color: $font-color-dark;
		}
	}

	.u-box {
		font-size: 28upx;
		color: $font-color-light;
		margin-top: 16upx;

		.name {
			margin-right: 30upx;
		}
	}

	.icon-bianji {
		display: flex;
		align-items: center;
		height: 80upx;
		font-size: 40upx;
		color: $font-color-light;
		padding-left: 30upx;
	}

	.add-btn {
		position: fixed;
		left: 30upx;
		right: 30upx;
		bottom: 16upx;
		z-index: 95;
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		font-size: 32upx;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
