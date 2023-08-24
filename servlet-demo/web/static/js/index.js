function delCust(id) {
    if (confirm('是否确认删除？')) {
        window.location.href = 'delete?id=' + id;
    }
}

function editCust(id) {
    window.location.href = 'edit?id=' + id;
}