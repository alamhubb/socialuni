var resizers = document.querySelectorAll(".resizer");
var currentResizer;
for (var i = 0; i < resizers.length; i++) {
    currentResizer = resizers[i];
    currentResizer.addEventListener('mousedown', function(e) {
        e.stopPropagation();
        currentResizer = e.target;
        document.addEventListener('mousemove', resize);
        document.addEventListener('mouseup', stopResize);
    });
}

function resize(e) {
    if (currentResizer.classList.contains("bottom-right")) {
        elmnt.style.width = e.clientX - elmnt.getBoundingClientRect().left + "px";
        elmnt.style.height = e.clientY - elmnt.getBoundingClientRect().top + "px";
    } else if (currentResizer.classList.contains("bottom-left")) {
        elmnt.style.width = elmnt.getBoundingClientRect().right - e.clientX + "px";
        elmnt.style.height = e.clientY - elmnt.getBoundingClientRect().top + "px";
        elmnt.style.left = e.clientX + "px";
    } else if (currentResizer.classList.contains("top-right")) {
        elmnt.style.width = e.clientX - elmnt.getBoundingClientRect().left + "px";
        elmnt.style.height = elmnt.getBoundingClientRect().bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
    } else if (currentResizer.classList.contains("top-left")) {
        elmnt.style.width = elmnt.getBoundingClientRect().right - e.clientX + "px";
        elmnt.style.height = elmnt.getBoundingClientRect().bottom - e.clientY + "px";
        elmnt.style.top = e.clientY + "px";
        elmnt.style.left = e.clientX + "px";
    }
}

function stopResize() {
    document.removeEventListener('mousemove', resize);
}