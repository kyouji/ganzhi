<!DOCTYPE html>
<head>
<meta charset="utf-8">
<title>荣诚手机超市--待付款订单</title>
<meta name="keywords" content="荣诚手机超市" />
<meta name="description" content="荣诚手机超市" />
<meta name="copyright" content="荣诚手机超市 版权所有" />
<link href="/css/layout.css" rel="stylesheet" type="text/css" />
<link href="/css/rcindex.css" rel="stylesheet" type="text/css" />
<link href="/css/member.css" rel="stylesheet" type="text/css" />
<!--[if IE]>
   <script src="/js/html5.js"></script>
<![endif]-->
<!--[if IE 6]>
<script type="text/javascript" src="/js/DD_belatedPNG_0.0.8a.js" ></script>
<script>
DD_belatedPNG.fix('.,img,background');
</script>
<![endif]-->
</head>
<body>
<header>
<div class="main">
	<#include "/front/comment/header.ftl">
</div>
</header>

<div class="header1 main">
<#include "/front/comment/header1.ftl">

</div></nav>

<div class="gwcbg">
<div class="main mt12">
<div class="member_lef fll"> <span class="sp55">订单中心</span>
      <ul class="haoh pt10">
        <li><a href="/order/list">全部订单</a></li>
        <li><a href="/order/obligation?status=0" class="act">待付款订单</a></li>
        <li><a href="/order/startorder?status=1">待收货订单</a></li>
        <li><a href="/order/orderok?status=3">已完成订单</a></li>
        <li><a href="/order/orderno?status=4">已关闭订单</a></li>
      </ul>
      <span class="mt10 sp55">个人中心</span>
      <ul class="haoh pt10">
        <li><a href="/user/info" >个人信息</a></li>
        <li><a href="/user/updatePassword" >密码修改</a></li>
        <li><a href="/user/address">收货地址</a></li>
        <li><a href="/user/collect">我的收藏</a></li>
        <li><a href="/user/point" >我的积分</a></li>
      </ul>
      <span class="mt10 sp55">客户服务</span>
      <ul class="haoh pt10 pb10">
        <li><a href="/help/question/1">帮助中心</a></li>
  
      </ul>
    </div>

<div class="flr right">
<div class="list_banner1">
<div class="place1"> <span>您现在的位置：</span><a href="/">首页</a>&gt;<a href="#">手机产品</a>&gt;<a href="#">手机筛选</a>&gt; </div>
        
  
        <div class="gwc1_lm mt15">
<table>
<tr>
<td>商品信息</td>
<td>合计</td>
<td>数量</td>
<td>状态</td>
<td>操作</td>
</tr>
</table>
</div>

<#list shopping_order_list as shopping_order>
		<div class="member_dd">
		<span class=" member_ddrq">2014-05-06<#if shopping_order.orderNumber??>
		    <span>订单号：${shopping_order.orderNumber}</span></#if>
		</span>
		<#list shopping_order.orderItemList as shopping_order_orderItemList>
		<table>
		<tr class="member_ddhe">
		<td class="member_dd_a"><span><a href="#"><img src="img/chdjfk.jpg" width="76" height="76"></a>
		    </span><a href="#"><#if shopping_order_orderItemList.productBrief??><p>${shopping_order_orderItemList.productBrief}</p></#if></a>
		</td>
		<td class="member_dd_b"><#if shopping_order_orderItemList.price??>
		    <span>${shopping_order_orderItemList.price}</span></#if>
		</td>
		<#if shopping_order_orderItemList??><td class="member_dd_c">${shopping_order_orderItemList.quantity}</td></#if>
		<#if shopping_order.statusCode??><td class="member_dd_d">${shopping_order.statusCode}</td></#if>
		<td class="member_dd_e"><a href="#">评价订单</a><a href="#">订单详情</a></td>
		</tr>
		</table>
		</#list>
		</div>
</#list>
</div>

        <div class="clear h20"></div>
        <div class="flr fot_fanye"><ul class="haoh">
        <li><a href="#" ><<</a></li>
         <li><a href="#" class="act">1</a></li>
          <li><a href="#">2</a></li>
           <li><a href="#">>></a></li>
        </ul></div>

        
        
        
    
        
</div></div>
</div>
</div>




<div class="clear"></div>

<div class="main mt20">
<#include "/front/comment/top.ftl">
</div>

<div class="clear"></div>

<div class="footallbg">
<#include "/front/comment/top1.ftl">

</div>






</body>
</html>




  











