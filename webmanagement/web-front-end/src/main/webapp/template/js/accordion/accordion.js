$(document).ready(function () {
    let acc = $(".accordion-dropdown");
    for (let i = 0; i < acc.length; i++) {
        acc[i].addEventListener("click", function () {
            this.classList.toggle("active");
            let panel = this.nextElementSibling;
            if (panel.style.maxHeight) {
                panel.style.maxHeight = null;
            } else {
                panel.style.maxHeight = panel.scrollHeight + "px";
                for (let j = 0; j < acc.length; j++) {
                    if (j !== i) {
                        acc[j].nextElementSibling.style.maxHeight = null;
                        acc[j].classList.remove('active');
                    }
                }
            }
        });
    }

});