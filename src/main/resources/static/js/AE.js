let ready = (mainFunction) => {
    if (typeof mainFunction === 'function') {
        document.addEventListener("DOMContentLoaded", mainFunction);
    } else {
        throw new Error("不是函数，无法执行");
    }
};