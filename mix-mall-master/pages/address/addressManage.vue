<template>
	<view class="content">
		<view class="row b-b">
			<text class="tit">联系人</text>
			<input class="input" type="text" v-model="addressData.name" placeholder="收货人姓名"
				placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">手机号</text>
			<input class="input" type="number" v-model="addressData.phone" placeholder="收货人手机号码"
				placeholder-class="placeholder" />
		</view>

		<view class="row b-b">
			<text class="tit">省份</text>
			<input class="input" type="text" v-model="addressData.province" placeholder="收货人省份"
				placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">市</text>
			<input class="input" type="text" v-model="addressData.city" placeholder="收货人市"
				placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">区</text>
			<input class="input" type="text" v-model="addressData.county" placeholder="收货人区"
				placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">镇</text>
			<input class="input" type="text" v-model="addressData.town" placeholder="收货人镇"
				placeholder-class="placeholder" />
		</view>
		<view class="row b-b">
			<text class="tit">详细地址（门牌号）</text>
			<input class="input" type="text" v-model="addressData.address" placeholder="收货人详细地址（门牌号）"
				placeholder-class="placeholder" />
		</view>
		<!-- <view class="row b-b">
			<text class="tit">详细地址（门牌号）</text>
			<text @click="chooseLocation" class="input">
				{{addressData.address}}
			</text>
			<text class="yticon icon-shouhuodizhi"></text>
		</view> -->

		<view class="row default-row">
			<text class="tit">设为默认</text>
			<switch :checked="addressData.isDefault==1" color="#fa436a" @change="switchChange" />
		</view>
		<button class="add-btn" @click="confirm">提交</button>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				addressData: {
					name: '',
					mobile: '',
					addressName: '在地图选择',
					address: '',
					area: '',
					default: false
				}
			}
		},
		onLoad(option) {
			let title = '新增收货地址';
			if (option.type === 'edit') {
				title = '编辑收货地址'

			}
			this.addressData = JSON.parse(option.data);
			this.manageType = option.type;
			uni.setNavigationBarTitle({
				title
			})
		},
		methods: {
			switchChange(e) {
				this.addressData.isDefault = e.detail.value;

				// this.addressData.isDefault = this.addressData.default==0?1:0;
			},

			//地图选择地址
			chooseLocation() {
				uni.chooseLocation({
					success: (data) => {
						this.addressData.addressName = data.name;
						this.addressData.address = data.name;
					}
				})
			},

			//提交
			confirm() {
				let data = this.addressData;
				if (!data.name) {
					this.$api.msg('请填写收货人姓名');
					return;
				}
				if (!/(^1[3|4|5|7|8][0-9]{9}$)/.test(data.phone)) {
					this.$api.msg('请输入正确的手机号码');
					return;
				}
				if (!data.province) {
					this.$api.msg('请填写收货人省份');
					return;
				}
				if (!data.city) {
					this.$api.msg('请填写收货人市');
					return;
				}
				if (!data.county) {
					this.$api.msg('请填写收货人区');
					return;
				}
				if (!data.address) {
					this.$api.msg('请填写详细地址');
					return;
				}


				uni.request({

					url: `${this.$baseUrl}/app/api/deliveryaddress/update`,

					method: 'PUT',

					data: data,

					success: (res) => {
						if (res.data.code == 0) {
							console.log(this.manageType + "用户收货地址成功");
							console.log(res);
							//this.$api.prePage()获取上一页实例，可直接调用上页所有数据和方法，在App.vue定义
							this.$api.prePage().refreshList(data, this.manageType);
							//提示更新或添加成功
							this.$api.msg(
								`${this.$baseUrl}/app/api/deliveryaddress/update${this.manageType=='edit' ? '修改': '添加'}成功`
								);
							//延时跳转
							setTimeout(() => {
								uni.navigateBack()
							}, 800)
						} else {
							console.log(this.manageType + "添加用户收货地址失败");
							console.log(res);
							this.$api.msg(res.msg);
						}

					},

				 fail: (err) => {
						console.log(this.manageType + "添加用户收货地址失败");
						console.log(res);
					}

				})

			},
		}
	}
</script>

<style lang="scss">
	page {
		background: $page-color-base;
		padding-top: 16upx;
	}

	.row {
		display: flex;
		align-items: center;
		position: relative;
		padding: 0 30upx;
		height: 110upx;
		background: #fff;

		.tit {
			flex-shrink: 0;
			width: 120upx;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.input {
			flex: 1;
			font-size: 30upx;
			color: $font-color-dark;
		}

		.icon-shouhuodizhi {
			font-size: 36upx;
			color: $font-color-light;
		}
	}

	.default-row {
		margin-top: 16upx;

		.tit {
			flex: 1;
		}

		switch {
			transform: translateX(16upx) scale(.9);
		}
	}

	.add-btn {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 690upx;
		height: 80upx;
		margin: 60upx auto;
		font-size: $font-lg;
		color: #fff;
		background-color: $base-color;
		border-radius: 10upx;
		box-shadow: 1px 2px 5px rgba(219, 63, 96, 0.4);
	}
</style>
