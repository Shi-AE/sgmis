export function toDebounceFunction(func, delay, ...args) {
    //默认300毫秒
    delay = delay || 300

    let timer;
    return function () {
        clearTimeout(timer);
        timer = setTimeout(() => {
            func.apply(this, args);
        }, delay);
    }
}