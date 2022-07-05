<template>
	<view class="content">
		<scroll-view scroll-y class="left-aside">
			<view v-for="item in categoryList" :key="item.catId" class="f-item b-b" :class="{active: item.catId === currentId}" @click="tabtap(item)">
				{{item.name}}
			</view>
		</scroll-view>
		<scroll-view scroll-with-animation scroll-y class="right-aside" @scroll="asideScroll" :scroll-top="tabScrollTop">
			<view v-for="item in slist" :key="item.catId" class="s-list" :id="'main-'+item.catId">
				<text class="s-item">{{item.name}}</text>
				<view class="t-list">
					<view @click="navToList(item.catId, titem.catId)" v-if="titem.parentCid === item.catId" class="t-item" v-for="titem in tlist" :key="titem.catId">
						<image :src="titem.icon"></image>
						<text>{{titem.name}}</text>
					</view>
				</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				sizeCalcState: false,
				tabScrollTop: 0,
				currentId: 1,
				categoryList:[],
				flist: [],
				slist: [],
				tlist: [],
			}
		},
		onLoad(){
			this.loadData();
		},
		methods: {
			async loadData(){
				let list = [];
				
				uni.request({
				
					url: `http://gulimall.com:88/api/product/category/list/tree`,
				
					method: 'GET',
			
					success: (res) => {
						console.log(res);
						this.$api.msg("获取分类成功:" + res.errMsg);
						if (res.data.code == 0) {
							this.categoryList = res.data.data;
							
							var obj = this.categoryList[0];
							//渲染 第一个一级分类下的子类
							obj.children.forEach(item=>{
								this.slist.push(item);
								if(item.children.length>0){
									item.children.forEach(item2=>{
										this.tlist.push(item2);
									}) 
								}
							}) 
						} 
					},
					fail: (err) => {
						console.log("获取分类失败");
						console.log(err)
					}
				})
				// 
				// let list = await this.$api.json('cateList');
				//3级分类
				// list.forEach(item=>{
				// 	if(item.catLevel==1){
				// 		this.flist.push(item); 
				// 	}else if(item.catLevel==2){
				// 		this.slist.push(item); 
				// 	}else{
				// 		this.tlist.push(item); 
				// 	}
				// }) 
			},
			//一级分类点击
			tabtap(currentCategory){
				//清空上一个 一级分类下的子类
				this.slist = [];
				this.tlist = [];
				//重新初始化 渲染
				currentCategory.children.forEach(item=>{
					this.slist.push(item);
					if(item.children.length>0){
						item.children.forEach(item2=>{
							this.tlist.push(item2);
						}) 
					}
				}) 
				// if(!this.sizeCalcState){
				// 	this.calcSize();
				// }
				//修改选中样式
				this.currentId = currentCategory.catId;
				// let index = this.slist.findIndex(sitem=>sitem.pid === item.id);
				// this.tabScrollTop = this.slist[index].top;
			},
			//右侧栏滚动
			asideScroll(e){
				if(!this.sizeCalcState){
					this.calcSize();
				}
				let scrollTop = e.detail.scrollTop;
				let tabs = this.slist.filter(item=>item.top <= scrollTop).reverse();
				if(tabs.length > 0){
          this.tabScrollTop = scrollTop;
					this.currentId = tabs[0].parentCid;
				}
			},
			//计算右侧栏每个tab的高度等信息
			calcSize(){
				let h = 0;
				this.slist.forEach(item=>{
					let view = uni.createSelectorQuery().select("#main-" + item.catId);
					view.fields({
						size: true
					}, data => {
						item.top = h;
						h += data.height;
						item.bottom = h;
					}).exec();
				})
				this.sizeCalcState = true;
			},
			navToList(sid, tid){
				uni.navigateTo({
					url: `/pages/product/list?fid=${this.currentId}&sid=${sid}&tid=${tid}`
				})
			}
		}
	}
</script>

<style lang='scss'>
	page,
	.content {
		height: 100%;
		background-color: #f8f8f8;
	}

	.content {
		display: flex;
	}
	.left-aside {
		flex-shrink: 0;
		width: 200upx;
		height: 100%;
		background-color: #fff;
	}
	.f-item {
		display: flex;
		align-items: center;
		justify-content: center;
		width: 100%;
		height: 100upx;
		font-size: 28upx;
		color: $font-color-base;
		position: relative;
		&.active{
			color: $base-color;
			background: #f8f8f8;
			&:before{
				content: '';
				position: absolute;
				left: 0;
				top: 50%;
				transform: translateY(-50%);
				height: 36upx;
				width: 8upx;
				background-color: $base-color;
				border-radius: 0 4px 4px 0;
				opacity: .8;
			}
		}
	}

	.right-aside{
		flex: 1;
		overflow: hidden;
		padding-left: 20upx;
	}
	.s-item{
		display: flex;
		align-items: center;
		height: 70upx;
		padding-top: 8upx;
		font-size: 28upx;
		color: $font-color-dark;
	}
	.t-list{
		display: flex;
		flex-wrap: wrap;
		width: 100%;
		background: #fff;
		padding-top: 12upx;
		&:after{
			content: '';
			flex: 99;
			height: 0;
		}
	}
	.t-item{
		flex-shrink: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		width: 176upx;
		font-size: 26upx;
		color: #666;
		padding-bottom: 20upx;
		
		image{
			width: 140upx;
			height: 140upx;
		}
	}
</style>
