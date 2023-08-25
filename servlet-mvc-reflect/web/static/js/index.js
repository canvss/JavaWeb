function delCust(id) {
    if (confirm('是否确认删除？')) {
        window.location.href = '/demo?method=deleteCustomerById&id=' + id;
    }
}

function editCust(id) {
    window.location.href = '/demo?method=editCustomerById&id=' + id;
}

function page(pageNo) {
    window.location.href = "/demo?method=customerList&pageNo=" + pageNo;
}