(function ($) {
    'use strict';
    $(function () {
        var body = $('body');
        var sidebar = $('.sidebar');

        //Add active class to nav-link based on url dynamically
        //Active class can be hard coded directly in html file also as required

        function addActiveClass(element) {
            if (current === "") {
                //for root url
                if (element.attr('href').indexOf("index.html") !== -1) {
                    element.parents('.nav-item').last().addClass('active');
                    if (element.parents('.sub-menu').length) {
                        element.closest('.collapse').addClass('show');
                        element.addClass('active');
                    }
                }
            } else {
                //for other url
                if (element.attr('href').indexOf(current) !== -1) {
                    element.parents('.nav-item').last().addClass('active');
                    if (element.parents('.sub-menu').length) {
                        element.closest('.collapse').addClass('show');
                        element.addClass('active');
                    }
                    if (element.parents('.submenu-item').length) {
                        element.addClass('active');
                    }
                }
            }
        }

        var current = location.pathname.split("/").slice(-1)[0].replace(/^\/|\/$/g, '');
        $('.nav li a', sidebar).each(function () {
            var $this = $(this);
            addActiveClass($this);
        })

        //Close other submenu in sidebar on opening any

        sidebar.on('show.bs.collapse', '.collapse', function () {
            sidebar.find('.collapse.show').collapse('hide');
        });


        //Change sidebar

        $('[data-toggle="minimize"]').on("click", function () {
            body.toggleClass('sidebar-icon-only');
        });

        //checkbox and radios
        $(".form-check label,.form-radio label").append('<i class="input-helper"></i>');
    });
})(jQuery);

(function ($) {
    'use strict';
    $(function () {
        $('[data-toggle="offcanvas"]').on("click", function () {
            $('.sidebar-offcanvas').toggleClass('active')
        });
    });
})(jQuery);

ready(() => {
    let title = $("title").text();

    let main = $("#main");
    main.remove();

    $(`
    <div class="container-scroller">
        <nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
            <div class="navbar-brand-wrapper d-flex justify-content-center">
                <div class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
                    <a class="navbar-brand brand-logo" style="width: 40px;" href=""><img src="../img/logo.jpg"
                            alt="logo"></a>
                    <a class="navbar-brand brand-logo-mini" href=""><img src="../img/logo.jpg" alt="logo"></a>
                    <button class="navbar-toggler navbar-toggler align-self-center" type="button"
                        data-toggle="minimize">
                        <span class="mdi mdi-sort-variant"></span>
                    </button>
                </div>
            </div>
            <div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
                <ul class="navbar-nav">
                    <li class="nav-item fs-2 text-primary">${title}</li>
                </ul>
                <ul class="navbar-nav navbar-nav-right">
                    <li class="nav-item nav-profile dropdown">
                        <a class="nav-link dropdown-toggle" href="" data-toggle="dropdown" id="profileDropdown">
                            <span class="nav-profile-name fs-2">root</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-right navbar-dropdown"
                            aria-labelledby="profileDropdown">
                            <a class="dropdown-item" href="setting.html">
                                <i class="mdi mdi-settings text-primary"></i>
                                <span class="text-center">设置</span>
                            </a>
                            <a class="dropdown-item">
                                <i class="mdi mdi-logout text-primary"></i>
                                <span class="text-center">退出</span>
                            </a>
                        </div>
                    </li>
                </ul>
                <button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button"
                    data-toggle="offcanvas">
                    <span class="mdi mdi-menu"></span>
                </button>
            </div>
        </nav>
        <div class="container-fluid page-body-wrapper">
            <nav class="sidebar sidebar-offcanvas" id="sidebar">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="home.html">
                            <i class="mdi mdi-home menu-icon"></i>
                            <span class="menu-title">首页</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="sg.html">
                            <i class="mdi mdi-view-headline menu-icon"></i>
                            <span class="menu-title">鸽子库</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="sgin.html">
                            <i class="mdi mdi-grid-large menu-icon"></i>
                            <span class="menu-title">血统库</span>
                        </a>
                    </li>
                </ul>
            </nav>
            <div class="main-panel">
            </div>
        </div>
    </div>
    `).appendTo("body");

    main.appendTo(".main-panel");
})