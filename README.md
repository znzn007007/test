拆分背景

1.互链部分功能独立于主系统功能，是一个单独的模块；

2.对于产品而言,互链对于公司有着重要的意义,且功能不断完善,需求更新速度较快


#### 解决思路以及流程

1.互链实体模型迁移

2.互链部分相关接口抽取

3.主系统部分代码改造

4.测试以及问题修复

#### 实施步骤

##### 项目结构构建

预计时间大概在半天左右

##### 实体迁移

<table>
	<tr>
		<th width="20%">实体名称</th>
		<th width="10%">字段</th>
        <th width="10%">字段类型</th>
        <th width="40%">字段描述</th>
		<th width="20%">实体说明</th>
	</tr>
	<tr>
		<td width="20%" rowspan="5">interlinkrelation</td>
		<td>sortNumber</td>
        <td>Integer</td>
        <td>所在配置中位置及第几行</td>
		<td width="20%" rowspan="5">搭配关系的实体</td>
	</tr>
    <tr>
		<td>mainItem</td>
        <td>InterlinkItem</td>
        <td>主款</td>
	</tr>
     <tr>
		<td>matchItems</td>
        <td>List[InterlinkItem](中括号说明用)</td>
        <td>搭配款</td>
	</tr>
     <tr>
		<td>disabledShopNicks</td>
        <td>String</td>
        <td>禁用的店铺名称</td>
	</tr>
    <tr>
		<td>groupName</td>
        <td>String</td>
        <td>分组的组名</td>
	</tr>
    <tr>
		<td width="20%" rowspan="3">InterlinkItem</td>
		<td>sortNumber</td>
        <td>int</td>
        <td>所在配置中位置</td>
		<td width="20%" rowspan="3">搭配配置的实体</td>
	</tr>
    <tr>
		<td>product</td>
        <td>Product</td>
        <td>产品</td>
	</tr>
     <tr>
		<td>picture</td>
        <td>ProductPicture</td>
        <td>产品图片</td>
	</tr>
    <tr>
		<td width="20%" rowspan="2">InvalidAchievement</td>
		<td>achievement</td>
        <td>String</td>
        <td>失效的商品id</td>
		<td width="20%" rowspan="2">记录淘系上货失效商品id</td>
	</tr>
    <tr>
		<td>removed</td>
        <td>boolean</td>
        <td>失效状态</td>
	</tr>
     <tr>
		<td width="20%">AnalyzeRecorder</td>
		<td>interruptedId</td>
        <td>Long</td>
        <td>记录检测到的产品id</td>
		<td width="20%">记录检测中断点</td>
	</tr>
     <tr>
		<td width="20%" rowspan="2">InterlinkRelationGroup</td>
		<td>merchantId</td>
        <td>Long</td>
        <td>商户id</td>
		<td width="20%" rowspan="2">记录搭配关系的分组信息</td>
	</tr>
    <tr>
		<td>name</td>
        <td>String</td>
        <td>分组名称</td>
	</tr>
     <tr>
		<td width="20%" rowspan="4">ProductDistributionCondition</td>
		<td>productId</td>
        <td>Long</td>
        <td>产品id</td>
		<td width="20%" rowspan="4">记录互链拓扑的查询数据</td>
	</tr>
    <tr>
		<td>code</td>
        <td>String</td>
        <td>产品货号</td>
	</tr>
    <tr>
		<td>distributionStatus</td>
        <td>DistributionStatus</td>
        <td>上货状态</td>
	</tr>
     <tr>
		<td>matchProductIds</td>
        <td>List[Long]</td>
        <td>存在链接关系的产品id</td>
	</tr>
</table>

注：此部分的话预计时间大概在半天时间．

##### 接口抽取

目前，搭配部分配置分为展示，创建，产品查询，删除，失效检测，互链拓扑等功能．对各个功能进行提取后接口提取的逐一说明：

<table>
	<tr>
		<th width="10%">接口</th>
		<th width="20%">入参</th>
        <th width="10%">输出参数</th>
        <th width="10%">涉及到的功能</th>
	</tr>
	<tr>
		<td>interlinkRelation查询接口</td>
		<td>List[product]，List[ProductVisionResourceCreator]，List[Distribution]</td>
		<td>List[InterlinkRelationResponse]</td>
        <td>展示，创建(注１)</td>
	</tr>
    <tr>
		<td>interlinkRelation创建接口</td>
		<td>product, relations</td>
		<td>success or fail</td>
        <td>创建(注２)</td>
	</tr>
     <tr>
		<td>interlinkRelation更新接口</td>
		<td>product, productId</td>
		<td>success or fail</td>
        <td>更新</td>
	</tr>
    <tr>
		<td>失效检测接口</td>
		<td>List[String]</td>
		<td>trueOrfalse</td>
        <td>产品状态是否失效</td>
	</tr>
      <tr>
		<td>relationGroup创建接口</td>
		<td>merchantId,name</td>
		<td>success or fail</td>
        <td>创建以及更新</td>
	</tr>
     <tr>
		<td>relationGroup查询接口</td>
		<td>merchantId,name</td>
		<td>InterlinkRelationGroup</td>
        <td>创建以及更新</td>
	</tr>
    <tr>
		<td>互联拓扑接口</td>
		<td>无</td>
		<td>InterlinkRelationGroup</td>
        <td>互链拓扑(注３)</td>
	</tr>
     <tr>
		<td>系统搭配推荐接口</td>
		<td>productId</td>
		<td>List[ProductPicture]</td>
        <td>系统推荐项目(注４)</td>
	</tr>
</table>


注意点一：此处涉及到的内容包含，产品颜色属性的提取，失效检测，返回参数的创建；以前此处存在性能问题，后通过更改批量处理已经处理完毕，提取时需要规避单一处理的代码．

注意点二：创建部分包含interlinkItem的创建，目前创建的关系多的话，需要２－３秒，抽取接口的过程中需对部分代码进行优化处理

注意点三：互链拓扑部分以前存在性能问题，优化失效检测之后已经优化；目前页面显示拖动之后会有杂线出现，待优化

注意点四：系统推荐部分为独立项目，保留接口

注：此部分的话预计时间大概在５天时间．

##### 数据迁移：

1.数据库采用postgresql,需要安装以及熟悉特性　预计时间１天；

2.需要保留数据迁移的接口，做成定时任务，对数据进行同步处理，预计时间１天；

3.历史数据可以将数据直接导出然后把数据导入pg

注：此部分的话预计时间大概在３天时间．

##### 待定问题

1.项目整合到原有的系统推荐中还是新建项目？

个人意见：新建项目

(1)目前的话，互链部分功能相对独立，增加的需求也是独立部分，业务上可以独立；

(2)系统推荐部分有增加复杂度的业务需求，保持独立，有益于增加新的业务需求

##### 注意点：

1.数据迁移过程中，可能存在两个数据库共存的情况，需要保证两个数据库中数据保持一致；

2.互链部分的话，目前主系统涉及到的模块有微调模块，详情页生成模块，上货模块，同步模块，图片包上传模块；其中上货模块和同步模块的话，无影响，其余模块涉及到的部分，分别为查询以及创建搭配关系

##### 时间点：

预估时间点：目前接口部分以及数据迁移预计时间大概10天；测试以及问题整改，预计5天；
