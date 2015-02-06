<div id="id-add" class="hide">
    <div class="form-group">
        <a href="javascript:void(0)" class="back btn btn-default btn-sm">返回上一级</a> 
    </div>

    <form id="fm-add" enctype="multipart/form-data" class="form-horizontal" role="form">
        <div class="col-sm-12">
            <label class="col-sm-1 control-label no-padding-right" for="type">类型</label>
                
            <div class="col-sm-3">
                <select class="form-control" id="type" name="type">
                    <option value="">请选择</option>
                    <#if type_list??>
                        <#list type_list as type>
                        <option value="${type.name}">${type.name}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        
        <div class="space-4"></div>
    
        <div class="col-sm-12" style="margin-top: 10px;">
            <label class="col-sm-1 control-label no-padding-right" for="name">商品名称</label>
    
            <div class="col-sm-5">
                <input type="text" id="name" name="name" class="col-xs-10 col-sm-12" />
            </div>
            
            <label class="col-sm-1 control-label no-padding-right" for="title">商品标题</label>
    
            <div class="col-sm-5">
                <input type="text" id="title" name="title" class="col-xs-10 col-sm-12" />
            </div>  
        </div>
            
        <div class="col-sm-12" style="margin-top: 10px;">
            <label class="col-sm-1 control-label no-padding-right" for="code">商品编号</label>
    
            <div class="col-sm-5">
                <input type="text" id="code" name="code" class="col-xs-10 col-sm-12" />
            </div>  
            
            <label class="col-sm-1 control-label no-padding-right" for="barCode">商品条码</label>
    
            <div class="col-sm-5">
                <input type="text" id="barCode" name="barCode" class="col-xs-10 col-sm-12" />
            </div>  
        </div>
        
        <!-- 属性块 -->
        <div id="props-section" class="col-sm-12" style="margin-top: 10px;"></div>
            
        <#--
        <div class="row">
        
            <label class="col-sm-1 control-label no-padding-right" for="isOnSale">是否上架</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="isOnSale" name="isOnSale" data-placeholder="是">
                    <option value="true">是</option>
                    <option value="false">否</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="isVirtualGoods">虚拟商品</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="isVirtualGoods" name="isVirtualGoods">
                    <option value="false">否</option>
                    <option value="true">是</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="isSupportPointToBuy">积分购买</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="isSupportPointToBuy" name="isSupportPointToBuy" data-placeholder="不支持">
                    <option value="false">不支持</option>
                    <option value="true">支持</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="pointsLimitedToBuy">积分限额</label>
            
            <div class="col-sm-2">
                <input type="text" id="pointsLimitedToBuy" name="pointsLimitedToBuy" class="col-sm-10" />
            </div> 
            
        </div>
        -->
    
        <div class="space-4"></div>
        
        <div class="col-sm-12" style="margin-top: 10px;">
            
            <label class="col-sm-1 control-label no-padding-right" for="coverImage">封面图片</label>
    
            <div class="col-sm-2">
                <input type="file" id="coverImage" name="coverImage" />
            </div>
            
            <label class="col-sm-1 control-label no-padding-right">展示图片</label>
            <div class="col-sm-2">
                <input type="file" multiple class="show-picture" name="pictures" />
            </div>
            
            <label class="col-sm-1 control-label no-padding-right" for="promotionPic">促销图片</label>
    
            <div class="col-sm-2">
                <input type="file" id="promotionPic" name="promotionPic" />
            </div>
        </div>
        
        <div class="space-4"></div>
        
        <!-- 价格块 -->
        <div class="col-sm-12" style="margin-top: 10px;">
            <div class="widget-box">
                <div class="widget-header widget-header-flat">
                    <h4 class="smaller col-sm-1">商品价格</h4>
                    
                    <label class="col-sm-1 control-label no-padding-right" for="priceUnit">单位</label>
                    
                    <div class="smaller col-sm-2" style="align:center;">
                        <select class="form-control" id="priceUnit" name="priceUnit" data-placeholder="元">
                            <option value="元">元</option>
                            <!--<option value="美元">美元</option>-->
                        </select>
                    </div> 
                    
                    <div style"float:right;" class="col-sm-2"> 
                        <a href="javascript:void(0)" id="id-add-version" class="btn btn-success btn-sm hide">增加版本</a> 
                    </div> 
                    
                    <div class="widget-toolbar">
                        
                        <label>
                            <small class="green">
                                <b>按版本定价</b>
                            </small>
    
                            <input id="isUseVersionPrice" name="isUseVersionPrice" type="checkbox" class="ace ace-switch ace-switch-6" value="true" />
                            <span class="lbl"></span>
                        </label>
                    </div>
                </div>
    
                <div class="widget-body">
                    <div class="widget-main">
                        <div class="row">
                            <label class="col-sm-1 control-label no-padding-right" for="costPrice">成本价</label>
    
                            <div class="col-sm-5">
                                <input type="text" id="costPrice" name="costPrice" class="price col-xs-10 col-sm-12" />
                            </div>  
                            
                            <label class="col-sm-1 control-label no-padding-right" for="marketPrice">市场价</label>
    
                            <div class="col-sm-5">
                                <input type="text" id="marketPrice" name="marketPrice" class="price col-xs-10 col-sm-12" />
                            </div>  
                        </div>
                        
                        <div class="space-4"></div>
                        
                        <!-- 统一定价 -->
                        <div id="simplePrice" class="row">
                            <label class="col-sm-1 control-label no-padding-right" for="salePrice">销售价</label>
    
                            <div class="col-sm-5">
                                <input type="text" id="salePrice" name="salePrice" class="price col-xs-10 col-sm-12" />
                            </div>  
                            
                            <label class="col-sm-1 control-label no-padding-right" for="number">数量</label>
                    
                            <div class="col-sm-5">
                                <input type="text" id="number" name="number" class="number col-xs-10 col-sm-12" />
                            </div>  
                        </div>
                        
                        <!-- 分版本定价 -->
                        <div id="versionPrice" class="row hide">
                        
                            <div class="col-sm-12">
                                <label class="col-sm-1 control-label" for="versionNames">版本名</label>
    
                                <input type="text" id="versionNames" name="versionNames" class="versionNames col-sm-2" />
                                 
                                <label class="col-sm-1 control-label" for="versionColors">颜色</label>
    
                                <input type="text" id="versionColors" name="versionColors" class="versionColors col-sm-1" />
                                
                                <label class="col-sm-1 control-label" for="versionCapacities">容量</label>
    
                                <input type="text" id="versionCapacities" name="versionCapacities" class="versionCapacities col-sm-1" />
                                
                                <label class="col-sm-1 control-label" for="versionPrices">价格</label>
                                    
                                <input type="text" id="versionPrices" name="versionPrices" class="price versionPrices col-sm-1" />
                                
                                <label class="col-sm-1 control-label" for="versionNumbers">数量</label>
                                    
                                <input type="text" id="versionNumbers" name="versionNumbers" class="number versionNumbers col-sm-1" />
                                
                                <label class="col-sm-1">
                                
                                <a href="javascript:void(0)" class="btn btn-grey btn-xs del-version">
                                    <i class="icon-trash icon-2x icon-only"></i>
                                </a>
                                </label>
                            </div>
                            
                        </div>
                        <!-- 分版本定价 -->
                    </div>
                </div>
            </div>
        </div> <!-- 价格块 -->
            
        <div class="space-4"></div> 
        
        <label class="col-sm-1 control-label no-padding-right" for="propDetail">商品参数</label>
        <input type="text" id="propDetail-input" name="propDetail" hidden></input>
        <div class="wysiwyg-editor" id="propDetail"></div>
        
        <div class="space-4"></div>
        
        <div class="form-group">
            <div class="col-sm-6">
                <label for="description">商品简述</label>
    
                <textarea class="form-control limited" id="description" name="description" maxlength="100"></textarea>
            </div>
            
            <div class="col-sm-6">
                <label for="config">配置说明</label>
    
                <textarea class="form-control limited" id="config" name="config" maxlength="100"></textarea>
            </div>
        </div>
        
        <div class="space-4"></div>
        
        <div class="form-group">
            <div class="col-sm-6">
                <label for="service">服务说明</label>
    
                <textarea class="form-control limited" id="service" name="service" maxlength="100"></textarea>
            </div>
            
            <div class="col-sm-6">
                <label for="deliverySpec">配送说明</label>
    
                <textarea class="form-control limited" id="deliverySpec" name="deliverySpec" maxlength="100"></textarea>
            </div>
        </div>
        
        <div class="space-4"></div>
        
        <div class="form-group">
            <label class="col-sm-1 control-label no-padding-right" for="form-field-tags">商品标签</label>
    
            <div class="col-sm-5">
                <input type="text" name="tags" id="form-field-tags" value="" placeholder="按回车录入标签 ..." />
            </div>
        </div>
        
        <div class="space-4"></div>
        
        <div class="form-group hide">
            
            <label class="col-sm-1 control-label no-padding-right" for="shop">商家</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="shopName" name="shopName">
                    <option selected value="">请选择</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="providerName">供货商</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="providerName" name="providerName">
                    <option selected value="">请选择</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="storehouseName">储存仓库</label>
            
            <div class="col-sm-2">
                <select class="form-control" id="storehouseName" name="storehouseName">
                    <option selected value="">请选择</option>
                </select>
            </div> 
            
            <label class="col-sm-1 control-label no-padding-right" for="warningMinNumber">库存预警值</label>
            
            <div class="col-sm-2">
                <input type="text" id="warningMinNumber" name="warningMinNumber" placeholder="0" class="col-xs-10 col-sm-10" />
            </div> 
            
        </div> <!-- /form-group -->
        
        <div class="space-4"></div>
        
        <div class="form-group hide">
            
            <label class="col-sm-1 control-label no-padding-right" for="putInStorageDate">入库日期</label>
    
            <div class="col-sm-2">
                <div class="input-group">
                    <input class="form-control date-picker" id="putInStorageDate" name="putInStorageDate" type="text" data-date-format="yyyy-mm-dd" />
                    <span class="input-group-addon">
                        <i class="icon-calendar bigger-110"></i>
                    </span>
                </div>
            </div>
            
            <label class="col-sm-1 control-label no-padding-right" for="validDate">过期日期</label>
    
            <div class="col-sm-2">
                <div class="input-group">
                    <input class="form-control date-picker" id="validDate" name="validDate" type="text" data-date-format="yyyy-mm-dd" />
                    <span class="input-group-addon">
                        <i class="icon-calendar bigger-110"></i>
                    </span>
                </div>
            </div>
        </div> <!-- /form-group -->
        
        <div class="space-4"></div>
        
        <label class="col-sm-1 control-label no-padding-right" for="detail">商品详情</label>
        <input type="text" id="detail-input" name="detail" hidden></input>
        <div class="wysiwyg-editor" id="detail"></div>
        
        <div class="space-4"></div>
        
        <div class="form-group">
            <div class="col-sm-10"></div>
            <a href="javascript:void(0)" id="add-submit" class="btn btn-success btn-sm col-sm-1">提交</a> 
        </div>
    </form>
</div><!-- dialog -->      