let ready = (mainFunction) => {
    if (typeof mainFunction === 'function') {
        document.addEventListener("DOMContentLoaded", mainFunction);
    } else {
        throw new Error("无法执行此函数");
    }
};

let popupType = {
    error: "error",
    warning: "warning",
    success: "success"
}

let color = {
    blue: "primary",
    gray: "secondary",
    green: "success",
    cyan: "info",
    yellow: "warning",
    red: "danger",
    white: "light",
    black: "dark"
}

let showMessagePopup = (init) => {
    let option = {
        type: init.type || popupType.success,
        id: init.id || "message",
        message: init.message || "信息"
    }

    if ($(`#${option.id}`).length !== 0) {
        throw new Error("id已存在，无法重复创建");
    }

    let modal;
    switch (option.type) {
        case popupType.error: modal = {
            modalTitle: "错误",
            modalDody: "danger"
        };
            break;
        case popupType.success: modal = {
            modalTitle: "成功",
            modalDody: "success"
        };
            break;
        case popupType.warning: modal = {
            modalTitle: "警告",
            modalDody: "warning"
        };
            break;
    }

    let popup = $(`
        <div class="modal fade" id="${option.id}" tabindex="-1" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5 text-${modal.modalDody}">${modal.modalTitle}</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body text-${modal.modalDody} bg-${modal.modalDody}-subtle border  border-${modal.modalDody}-subtle">
                        ${option.message}
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">关闭</button>
                    </div>
                </div>
            </div>
        </div>
    `).appendTo("body")
    
    popup.on("hidden.bs.modal", () => {
        popup.remove();
    });

    new bootstrap.Modal(`#${option.id}`).show();
};

let Spinner = (initColor) => {
    let spinnerColor = initColor || color.black;
    if ($(`#${spinnerColor}-spinner`).length > 0) {
        throw new Error("该颜色已被创建 ");
    }
    let spinner = $(`
    <div id="${spinnerColor}-spinner" class="d-flex justify-content-center align-items-center" style="height: 100%; width: 100%; position: absolute; left: 0; top: 0; z-index: 1; background-color: rgba(0, 0, 0, 0.4);">
        <div class="spinner-border text-${spinnerColor}" role="status"></div>
    </div>
    `);
    spinner.appendTo("body");
    return {
        show: () => {
            spinner.show();
        },
        hide: () => {
            spinner.hide();
        },
        remove: () => {
            spinner.remove();
        }
    };
};