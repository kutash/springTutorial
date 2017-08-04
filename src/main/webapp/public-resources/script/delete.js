function onDeleteClick(event) {
    console.log("hi");
    var doDelete = confirm("Are you sure you want to delete this offer?");

    if(doDelete == false) {
        event.preventDefault();
    }
}

function onReady() {

    $(".delete").click(onDeleteClick);
}

$(document).ready(onReady);
