if(!self.__appxInited) {
self.__appxInited = 1;


require('./config$');


var AFAppX = self.AFAppX.getAppContext
  ? self.AFAppX.getAppContext().AFAppX
  : self.AFAppX;
self.getCurrentPages = AFAppX.getCurrentPages;
self.getApp = AFAppX.getApp;
self.Page = AFAppX.Page;
self.App = AFAppX.App;
self.my = AFAppX.bridge || AFAppX.abridge;
self.abridge = self.my;
self.Component = AFAppX.WorkerComponent || function(){};
self.$global = AFAppX.$global;
self.requirePlugin = AFAppX.requirePlugin;
        


function success() {
require('../../app');
require('../../components/share?hash=ec7c5687c7f2ffa836caf3a93e877914426baff6');
require('../../components/uni-number-box?hash=ec7c5687c7f2ffa836caf3a93e877914426baff6');
require('../../components/mix-list-cell?hash=ec7c5687c7f2ffa836caf3a93e877914426baff6');
require('../../components/uni-load-more/uni-load-more?hash=ec7c5687c7f2ffa836caf3a93e877914426baff6');
require('../../components/empty?hash=ec7c5687c7f2ffa836caf3a93e877914426baff6');
require('../../pages/index/index?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/product/product?hash=d015e0b8d0ef02defd079defe56388ca69f7de9d');
require('../../pages/set/set?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/userinfo/userinfo?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/cart/cart?hash=76da5671077d9791763cd70748eae5168883567f');
require('../../pages/public/login?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/user/user?hash=8d548cc6e371421909838bea9d0858355f994268');
require('../../pages/detail/detail?hash=d015e0b8d0ef02defd079defe56388ca69f7de9d');
require('../../pages/order/order?hash=63c308e6ba15e4838f851304bc7880b8dc9e60ea');
require('../../pages/money/money?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/order/createOrder?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/address/address?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/address/addressManage?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/money/pay?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/money/paySuccess?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/notice/notice?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/category/category?hash=32d7d2807ed4e666ef03b4b3fe8c38ecf2e34e68');
require('../../pages/product/list?hash=548cdad7a262b25f01107ed1c1dbcecfeb45a493');
}
self.bootstrapApp ? self.bootstrapApp({ success }) : success();
}