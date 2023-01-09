<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>table</title>
</head>
<style>
    html,body {
        margin: 0;
        padding: 0;
        font-size: 11px;
        background-color: rgb(245,247,249);
        width: 800px;
        height: 1130px;
    }

    .box {
        width: 660px;
        height: 985px;
        margin: 30px auto;
        padding: 3px;
        background-color: #fff;
    }
    h2 p {
        text-align: center;
    }
    p {
        margin: 0;
    }
    table  {
        width: 100%;
        border-collapse: collapse;
    }
    table tr td { border:1px solid #000;padding: 5px; font-size: 10px}
    table tr .borderBottom {
        border-bottom: none !important;
    }
    .text {
        display: flex;
        width: 100%;
    }
    .text div {
        border: 1px dashed #ccc;
        border-top: none;
        flex: 1;
        font-size: 14px;
        line-height: 22px;
        padding: 10px;
    }
    .text div:nth-child(1) {
        border-right: none;
        padding-right: 60px;
    }
    .center tr {
        display: flex;
        width: 100%;
        height: 56px;
    }

    .center td{
        display: inline-block;
        overflow: hidden;
        border:1px solid #000;padding: 5px; font-size: 11px;
        vertical-align:middle;
        word-break: break-word;
    }
</style>
<body>
<div class="box">
    <h2>
        <p>集装箱装运危险货物装箱证明书</p>
        <p>CONTAINER PACKING CERTIFICATE </p>
    </h2>
    <table>
        <tr>
            <td height="30px">
                <div>船名：${shipNameCn}</div>
                <div>Ship's Name:
                    <#if shipNameEn??>
                        ${shipNameEn}
                    </#if>
                </div>
            </td>
            <td>
                <div>航次：${voyage}</div>
                <div>Voyage No.:</div>
            </td>
            <td>
                <div>目的港： ${destinationName}</div>
                <div>Port of Destination: ${destinationCode}</div>
            </td>
        </tr>
        <tr>
            <td colspan="3" height="30px">
                <p>集装箱编号： ${ctnNo}</p>
                <p>Container Serial No.:</p>
            </td>
        </tr>
        <tr>
            <td colspan="3" class="borderBottom" height="30px" style="text-align: center;">
                <p>箱内所装危险货物</p>
                <p>Dangerous Goods Packed Therein</p>
            </td>
        </tr>
    </table>
    <table style="height: 30px">
        <tr style="text-align: center;">
            <td  width="90px" style="text-align: center;">
                正确运输名称<br/>Proper Shipping Name of the Goods
            </td>
            <td width="55px" style="text-align: center;">
                货物类别<br/>IMDG Code Class
            </td>
            <td width="70px" style="text-align: center;">
                危规编号<br/>UN No.
            </td>
            <td  width="40px" style="text-align: center;">
                包装类<br/>Packing Group
            </td>
            <td width="75px" style="text-align: center;">
                件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数<br/>Package Quantity
            </td>
            <td  width="75px" style="text-align: center;">
                箱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数<br/>Total Container
            </td>
            <td width="55px" style="text-align: center;">
                总&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重<br/>Total Weight
            </td>
        </tr>
        <#if clPrintInfoBoList?size == 3>
            <#list clPrintInfoBoList as u>
                <tr  style="display:flex;height: 40px;">
                    <td height="50%">${u.goodsNameCn}</td>
                    <td>${u.hazardClass}</td>
                    <td>${u.unNo}</td>
                    <td>${u.packageKind}</td>
                    <td>${u.packageNum}</td>
                    <td>${u.totalNum}</td>
                    <td>${u.netWeight}</td>
                </tr>
            </#list>
        </#if>
        <#if clPrintInfoBoList?size == 2>
            <#list clPrintInfoBoList as u>
                <tr style="height: 30px;">
                    <td>${u.goodsNameCn}</td>
                    <td>${u.hazardClass}</td>
                    <td>${u.unNo}</td>
                    <td>${u.packageKind}</td>
                    <td>${u.packageNum}</td>
                    <td>${u.totalNum}</td>
                    <td>${u.netWeight}</td>
                </tr>
            </#list>
            <tr style="height: 30px;">
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
            </tr>
        </#if>
        <#if clPrintInfoBoList?size == 1>
            <#list clPrintInfoBoList as u>
                <tr style="height: 30px;">
                    <td>${u.goodsNameCn}</td>
                    <td>${u.hazardClass}</td>
                    <td>${u.unNo}</td>
                    <td>${u.packageKind}</td>
                    <td>${u.packageNum}</td>
                    <td>${u.totalNum}</td>
                    <td>${u.netWeight}</td>
                </tr>
            </#list>
            <tr style="height: 30px;">
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
            </tr>
            <tr style="height: 30px;">
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
                <td ></td>
            </tr>
        </#if>
    </table>
    <div class="text" style="border: 1px">
        <div style="width: 2px; float: left;"></div>
        <div style="width: 250px; float: left;">
            <p>兹证明：装箱现场检查员已根据《国际海运危险货
                物规则》的要求，对上述集装箱和箱内所装危险货
                物及货物在箱内的积载情况进行了检查。并声明如
                下：</p>
            <p>1.&emsp;&nbsp;&nbsp;集装箱清洁、干燥、外观上适合装货。</p>
            <p>2.&emsp;&nbsp;&nbsp;如果托运货物中包括除第1.4类外的第1类货
                物，集装箱在结构上符合《国际危规》第1卷
                第7.4.6节的规定。</p>
            <p>3.&emsp;&nbsp;&nbsp;集装箱内未装有不相容的物质，除经有关主管
                机关按第1卷第7. 2.2.3节的规定批准者外。</p>
            <p>4.&emsp;&nbsp;&nbsp;所有包件均已经过外观破损检查，装箱的包件
                完好无损。</p>
            <p>5.&emsp;&nbsp;&nbsp;所有包件装箱正确，衬垫、加固合理。</p>
            <p>6.&emsp;&nbsp;&nbsp;当散装危险货物装入集装箱时，货物已均匀地
                分布在集装箱内。</p>
            <p>7.&emsp;&nbsp;&nbsp;集装箱和所装入的包件均已正确地加以标记、
                标志和标牌。</p>
            <p>8.&emsp;&nbsp;&nbsp;当将固体二氧化碳（干冰）用于冷却目的时，
                在集装箱外部队门端明显处已显示标记或标志。
                注明：“内有危险气体—二氧化碳（干冰），
                进入之前务必彻底通风。”</p>
            <p>9.&emsp;&nbsp;&nbsp;对集装箱内所装的每票危险货物,已经收到根
                据《国际危装》第1卷第5.4.1节所要求的危
                险货物申报单。</p>
            <h3>以上各项准确无误。</h3>
            <p>装箱现场检查员签字：</p>
            <p>Signature of packing inspector:</p>
            <p>装箱现场检查员证书编号：${inspectorCert}</p>
            <p>No. of certificate of packing inspector:</p>
            <p>装箱日期：${dateToString}</p>
            <p>Date of packing:</p>
        </div>
        <div style="width: 40px; float: left;"></div>
        <div style="width: 355px; float: left;">
            <p>This is to certify that the above mentioned container,dangerous goods packed therein and their stowage condition have been inspected by the undersigned packing inspector according to the provisions of INTERNATIONAL MARTITIME DANGEROUS GOODS CODE and to declare that:</p>
            <p>1.&emsp;&nbsp;&nbsp;This container was clean, dry and apparently fit to receive the goods.</p>
            <p>2.&emsp;&nbsp;&nbsp;If the consignments includes goods of class 1 except division 1.4, the container is structurally serviceable in conformity with section 7.4.6, volume 1 of the IMDG Code.</p>
            <p>3.&emsp;&nbsp;&nbsp;No incompatible goods have been packed into container, unless approved by the competent authority concerned in accordance with section 7.2.2.3 ,volume 1 of the IMDG Code.</p>
            <p>4.&emsp;&nbsp;&nbsp;All packages have been externally inspected for damage,and only sound package have been packed.</p>
            <p>5.&emsp;&nbsp;&nbsp;All packages have been properly packed in container and secured, dunnaged..</p>
            <p>6.&emsp;&nbsp;&nbsp;When dangerous goods are transported in bulk, the cargo has been evenly distributed in the container.</p>
            <p>7.&emsp;&nbsp;&nbsp;The container and packages therein are properly marked, labelled and placarded.</p>
            <p>8.&emsp;&nbsp;&nbsp;When solid carbon dioxide (dry ice) is used for cooling purpose, the container is externally marked or labelled in a conspicuous place at the door and ,with the words: “DANGEROUS CO2-GAS (DRY ICE) INSIDE, VENTILATE THOROUGHLY BEFORE ENTERING”.</p>
            <p>9.&emsp;&nbsp;&nbsp;The dangerous goods declaration required in subsection 5.4.1, volume 1 of the IMDG Code has been received for each dangerous goods consignment packed in the container.</p>
            <br/>
            <p>That all stated above are correct.</p>
            <p>检查地点：</p>
            <p>Place of Inspection:</p>
            <p>装箱单位（公章）：</p>
            <p>Packing unit(seal):</p>
            <p>签 发 日 期：</p>
            <p>Date of Issue:</p>
        </div>
    </div>
<#--    <div style="border: 1px solid #000000; padding: 5px;margin-top: 5px">-->
<#--        <p>紧急联系人姓名、电话、传真、电子邮箱：</p>-->
<#--        <p>Emergency Contact Person’s Name,Tel, Fax and E-mail:</p>-->
<#--    </div>-->
    <table style="border: 1px solid #000000; padding: 5px;margin-top: 5px">
        <tr>
            <td colspan="3" height="25px">
                <p>紧急联系人姓名、电话、传真、电子邮箱：</p>
                <p>Emergency Contact Person’s Name,Tel, Fax and E-mail:</p>
            </td>
        </tr>
    </table>
    <p>此证明书应由装箱现场检查员填写一式两份，一份于集装箱装船三天前向海事主管机关提交，另一份应在办理集装箱移交时交承人。</p>
    <p>Two copies of the certificate should be filled by the packing inspector. One should be submitted to Maritime Safety Administration three days prior to shipment and the other should be given to the carrier on container delivery.   </p>
</div>
</body>
</html>