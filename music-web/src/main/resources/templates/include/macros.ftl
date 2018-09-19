<#--公共顶部-->
<#macro header title="My音乐馆 - 张亚东博客" keywords="默认文字" description="默认文字">
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=600px,initial-scal=1,user-scalable=0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>${title}</title>
    <meta name="keywords" content="${keywords}"/>
    <meta name="description" content="${description}"/>
    <link href="/images/favicon.ico" rel="shortcut icon" type="image/x-icon">
    <link href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="/css/zhyd.music.core.css">
    <#nested>
</head>
<body>
</#macro>

<#--公共底部-->
<#macro footer>
    <footer>
        <p class="keTxtP">
            <strong> ©${.now?string("yyyy")}&nbsp;music.zhyd.me&nbsp;<a href="http://music.zhyd.me" target="_blank" title="My音乐馆">My音乐馆</a>&nbsp;<a href="https://www.zhyd.me" target="_blank" title="我的博客">我的博客</a> </strong> Powered by <a href="https://www.zhyd.me" target="_blank">张亚东</a><span id='cnzz_stat_icon_1273049101' style="margin-left: 10px;position: relative;top: 2px;"></span>
        </p>
        <#--<span id='cnzz_stat_icon_1273049101'></span>-->
    </footer>
    <a class="goTop" title="返回顶部"></a>

    <script src="/js/jquery.min.js?v=1.0.1" type="text/javascript"></script>
    <!--[if IE]><script src="http://apps.bdimg.com/libs/html5shiv/r29/html5.min.js"></script><![endif]-->
    <script src="/js/jquery-ui.min.js?v=1.0.1" type="text/javascript"></script>
    <script src="/js/jquery.toTop.min.js?v=1.0.1" type="text/javascript"></script>
    <script src="/js/star.js?v=1.0.1" type="text/javascript"></script>

    <#nested>

    <script>
        var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?addbf6bfea43ad7c5b9a5049add57d03";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();
    </script>
    <script>
        window._bd_share_config = {
            "common": {
                "bdSnsKey": {},
                "bdText": "",
                "bdMini": "2",
                "bdMiniList": ["qzone", "tsina", "weixin", "sqq", "mshare", "bdysc", "renren", "tqq", "bdxc", "kaixin001", "tqf", "tieba", "douban", "bdhome", "thx", "ibaidu", "meilishuo", "mogujie", "diandian", "huaban", "duitang", "hx", "fx", "youdao", "sdo", "qingbiji", "people", "xinhua", "mail", "isohu", "yaolan", "wealink", "ty", "iguba", "fbook", "twi", "linkedin", "h163", "evernotecn", "copy", "print"],
                "bdPic": "",
                "bdStyle": "0",
                "bdSize": "16"
            }, "slide": {"type": "slide", "bdImg": "3", "bdPos": "right", "bdTop": "203.5"}
        };
        with (document) 0[(getElementsByTagName('head')[0] || body).appendChild(createElement('script')).src = 'http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion=' + ~(-new Date() / 36e5)];
    </script>
    <#--<script type="text/javascript">-->
        <#--var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");-->
        <#--document.write(unescape("%3Cscript src='" + cnzz_protocol + "s19.cnzz.com/z_stat.php%3Fid%3D1273049101%26online%3D1%26show%3Dline' type='text/javascript'%3E%3C/script%3E"));</script>-->

    <script type="text/javascript">
        var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
        document.write(unescape("%3Cscript src='" + cnzz_protocol + "s19.cnzz.com/z_stat.php%3Fid%3D1273049101%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
    </script>
</body>
</html>
</#macro>