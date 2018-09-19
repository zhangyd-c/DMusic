(function ($) {
    /*显示歌词部分*/
    var tflag = 0;//存放时间和歌词的数组的下标
    var lytext = [];//放存汉字的歌词
    var lytime = [];//存放时间
    console.log("%c生活真他妈好玩，因为生活老他妈玩我！", "color:green;font-size:20px;font-weight:blod");
    console.groupEnd();
    console.log("别扒了，本站早就开源了：https://gitee.com/yadong.zhang/music");
    console.log("地址告诉你了， 爱看不看");
    // Settings
    var repeat = localStorage.repeat || 0,
            shuffle = localStorage.shuffle || 'false',
            continous = true,
            autoplay = true,
            playlist = [
                {
                    name: '肩上蝶',
                    author: '金志文',
                    albumName: '肩上蝶',
                    albumImg: '/images/1.gif',
                    url: 'http://rm.sina.com.cn/wm/VZ2010050511043310440VK/music/MUSIC1005051622027270.mp3',
                },
                {
                    name: '我可以抱你吗',
                    author: '张惠妹',
                    albumName: '我可以抱你吗',
                    albumImg: '/images/132731360220331_4.jpg',
                    url: 'http://rm.sina.com.cn/wm/VZ200812161100307607VK/music/MUSIC0812161100379667.mp3',
                },
                {
                    name: '一万个舍不得',
                    author: '庄心妍',
                    albumName: '一万个舍不得',
                    albumImg: '/images/20130325084808733.jpg',
                    url: 'http://rm.sina.com.cn/wm/VZ200812161100307607VK/music/MUSIC0812161100379667.mp3',
                }];
    $.ajax({
        type: "get",
        async: false,
        url: "/playList",
        success: function (json) {
            if (json != null) {
                playlist = json;
            }
        },
        error: function () {
            alert("网络异常");
        }
    });
    if (playlist.length <= 0) {
        $('#playlist').html("<strong><span>列表加载失败，请刷新重试~~~</span></strong>");
        return;
    }
    $('#playlist').html("");
    // Load playlist
    for (var i = 0; i < playlist.length; i++) {
        var item = playlist[i];
        $('#playlist').append('<li>' + item.author + ' - ' + item.name + '</li>');
    }

    var time = new Date(),
            currentTrack = shuffle === 'true' ? time.getTime() % playlist.length : 0,
            trigger = false,
            audio, timeout, isPlaying, playCounts;

    var play = function () {
        audio.play();
        ready2Play($(audio).attr("data-id"));//准备播放
        showLyr();//显示歌词
        $('.playback').addClass('playing');
        timeout = setInterval(updateProgress, 500);
        isPlaying = true;
    };


    var pause = function () {
        audio.pause();
        $('.playback').removeClass('playing');
        clearInterval(updateProgress);
        isPlaying = false;
    };

    // Update progress
    var setProgress = function (value) {
        var currentSec = parseInt(value % 60) < 10 ? '0' + parseInt(value % 60) : parseInt(value % 60),
                ratio = value / audio.duration * 100;

        $('.timer').html(parseInt(value / 60) + ':' + currentSec);
        $('.progress .pace').css('width', ratio + '%');
        $('.progress .slider a').css('left', ratio + '%');
    };

    var updateProgress = function () {
        setProgress(audio.currentTime);
    };

    // Progress slider
    $('.progress .slider').slider({
        step: 0.1, slide: function (event, ui) {
            $(this).addClass('enable');
            setProgress(audio.duration * ui.value / 100);
            clearInterval(timeout);
        }, stop: function (event, ui) {
            audio.currentTime = audio.duration * ui.value / 100;
            $(this).removeClass('enable');
            timeout = setInterval(updateProgress, 500);
        }
    });

    // Volume slider
    var setVolume = function (value) {
        audio.volume = localStorage.volume = value;
        $('.volume .pace').css('width', value * 100 + '%');
        $('.volume .slider a').css('left', value * 100 + '%');
    };

    var volume = localStorage.volume || 0.5;
    $('.volume .slider').slider({
        max: 1, min: 0, step: 0.01, value: volume, slide: function (event, ui) {
            setVolume(ui.value);
            $(this).addClass('enable');
            $('.mute').removeClass('enable');
        }, stop: function () {
            $(this).removeClass('enable');
        }
    }).children('.pace').css('width', volume * 100 + '%');

    $('.mute').click(function () {
        if ($(this).hasClass('enable')) {
            setVolume($(this).data('volume'));
            $(this).removeClass('enable');
        } else {
            $(this).data('volume', audio.volume).addClass('enable');
            setVolume(0);
        }
    });

    // Switch track
    var switchTrack = function (i) {
        if (i < 0) {
            track = currentTrack = playlist.length - 1;
        } else if (i >= playlist.length) {
            track = currentTrack = 0;
        } else {
            track = i;
        }

        $('audio').remove();
        loadMusic(track);
        if (isPlaying == true) play();
    };

    // Shuffle
    var shufflePlay = function () {
        var time = new Date(),
                lastTrack = currentTrack;
        currentTrack = time.getTime() % playlist.length;
        if (lastTrack == currentTrack) ++currentTrack;
        switchTrack(currentTrack);
    };

    // Fire when track ended
    var ended = function () {
        pause();
        audio.currentTime = 0;
        playCounts++;
        if (continous == true) isPlaying = true;
        if (repeat == 1) {
            play();
        } else {
            if (shuffle === 'true') {
                shufflePlay();
            } else {
                if (repeat == 2) {
                    switchTrack(++currentTrack);
                } else {
                    if (currentTrack < playlist.length) switchTrack(++currentTrack);
                }
            }
        }
    };

    var beforeLoad = function () {
        var endVal = this.seekable && this.seekable.length ? this.seekable.end(0) : 0;
        $('.progress .loaded').css('width', (100 / (this.duration || 1) * endVal) + '%');
    };

    // Fire when track loaded completely
    var afterLoad = function () {
        if (autoplay == true) play();
    };

    // Load track
    var loadMusic = function (i) {
        var item = playlist[i],
                newaudio = $('<audio data-id="' + item.id + '">').html('<source src="' + item.url + '">').appendTo('#player');
        $('.cover').html('<img src="' + item.albumImg + '" alt="' + item.albumName + '">');
        $('.tag').html('<strong>' + item.name + '</strong><span class="artist">' + item.author + '</span><span class="album">' + item.albumName + '</span>');
        $('#playlist li').removeClass('playing').eq(i).addClass('playing');
        audio = newaudio[0];
        audio.volume = $('.mute').hasClass('enable') ? 0 : volume;
        audio.addEventListener('progress', beforeLoad, false);
        audio.addEventListener('durationchange', beforeLoad, false);
        audio.addEventListener('canplay', afterLoad, false);
        audio.addEventListener('ended', ended, false);
    };

    loadMusic(currentTrack);
    $('.playback').on('click', function () {
        if ($(this).hasClass('playing')) {
            pause();
        } else {
            play();
        }
    });
    $('.rewind').on('click', function () {
        if (shuffle === 'true') {
            shufflePlay();
        } else {
            switchTrack(--currentTrack);
        }
    });
    $('.fastforward').on('click', function () {
        if (shuffle === 'true') {
            shufflePlay();
        } else {
            switchTrack(++currentTrack);
        }
    });
    $('#playlist li').each(function (i) {
        var _i = i;
        $(this).on('click', function () {
            switchTrack(_i);
        });
    });

    if (shuffle === 'true') $('.shuffle').addClass('enable');
    if (repeat == 1) {
        $('.repeat').addClass('once');
    } else if (repeat == 2) {
        $('.repeat').addClass('all');
    }

    $('.repeat').on('click', function () {
        if ($(this).hasClass('once')) {
            repeat = localStorage.repeat = 2;
            $(this).removeClass('once').addClass('all');
        } else if ($(this).hasClass('all')) {
            repeat = localStorage.repeat = 0;
            $(this).removeClass('all');
        } else {
            repeat = localStorage.repeat = 1;
            $(this).addClass('once');
        }
    });

    $('.shuffle').on('click', function () {
        if ($(this).hasClass('enable')) {
            shuffle = localStorage.shuffle = 'false';
            $(this).removeClass('enable');
        } else {
            shuffle = localStorage.shuffle = 'true';
            $(this).addClass('enable');
        }
    });

    /* 华丽分隔线-------------------------------------------显示歌词 start zyd */

    //开始播放
    function showLyr() {
        var ms = audio.currentTime;
        show(ms);
        window.setTimeout(function () {
            showLyr();
        }, 1000)
    }

    //取得歌词
    function getLy(s) {
        var ly = "";
        $.ajax({
            type: "POST",
            async: false,
            url: "/lrc/" + s,
            success: function (json) {
                if (json.status == 200) {
                    ly = json.data;
                    if ($.trim(ly) == "") {
                        ly = "[00:00] .[00:02]歌词暂未收录";
                    }
                } else if (json.status == 404) {
                    ly = "[00:00] .[00:02]歌词暂未收录";
                }
            },
            error: function (msg) {
                ly = "[00:00] .[00:02]歌词暂未收录";
            }
        });
        return ly;
    }

    //显示歌词
    function show(t) {
        var div1 = document.getElementById("lyr");//取得层
        document.getElementById("lyr").innerHTML = " ";//每次调用清空以前的一次
        if (t < lytime[lytime.length - 1])//先舍弃数组的最后一个
        {
            for (var k = 0; k < lytext.length; k++) {
                if (lytime[k] <= t && t < lytime[k + 1]) {
                    //scrollh=k*25;//让当前的滚动条的顶部改变一行的高度
                    div1.innerHTML += "<font color=#f60 style=font-weight:bold>" + lytext[k] + "</font><br>";
                }
            }
        }
        //加上数组的最后一个
        else {
            for (var j = 0; j < lytext.length - 1; j++)
                div1.innerHTML += lytext[j] + "<br>";
            div1.innerHTML += "<font color=red style=font-weight:bold>" + lytext[lytext.length - 1] + "</font><br>";
        }
    }

    //在显示歌词前做好准备工作
    function ready2Play(s) {
        var ly = getLy(s);//得到歌词
        if (ly == "") {
            $("#lry").html("本歌暂无歌词！");
        }
        var arrly = ly.split(".");//转化成数组
        tflag = 0;
        for (var i = 0; i < lytext.length; i++) {
            lytext[i] = "";
        }
        for (var i = 0; i < lytime.length; i++) {
            lytime[i] = "";
        }
        $("#lry").html(" ");
        document.getElementById("lyr").scrollTop = 0;
        for (var i = 0; i < arrly.length; i++)
            sToArray(arrly[i]);
        sortAr();
    }

    //解析如“[02:02][00:24]没想到是你”的字符串前放入数组
    function sToArray(str) {

        var left = 0;//"["的个数
        var leftAr = new Array();
        for (var k = 0; k < str.length; k++) {
            if (str.charAt(k) == "[") {
                leftAr[left] = k;
                left++;
            }
        }
        if (left != 0) {
            for (var i = 0; i < leftAr.length; i++) {
                lytext[tflag] = str.substring(str.lastIndexOf("]") + 1);//放歌词
                lytime[tflag] = conSeconds(str.substring(leftAr[i] + 1, leftAr[i] + 6));//放时间
                tflag++;
            }
        }
    }

    //按时间重新排序时间和歌词的数组
    function sortAr() {
        var temp = null;
        var temp1 = null;
        for (var k = 0; k < lytime.length; k++) {
            for (var j = 0; j < lytime.length - k; j++) {
                if (lytime[j] > lytime[j + 1]) {
                    temp = lytime[j];
                    temp1 = lytext[j];
                    lytime[j] = lytime[j + 1];
                    lytext[j] = lytext[j + 1];
                    lytime[j + 1] = temp;
                    lytext[j + 1] = temp1;
                }
            }
        }
    }

    //把形如：01：25的时间转化成秒；
    function conSeconds(t) {
        var m = t.substring(0, t.indexOf(":"));
        var s = t.substring(t.indexOf(":") + 1);
        m = parseInt(m.replace(/0/, ""));
        return parseInt(m) * 60 + parseInt(s);
    }

    /* 华丽分隔线-------------------------------------------显示歌词 end */
})(jQuery);