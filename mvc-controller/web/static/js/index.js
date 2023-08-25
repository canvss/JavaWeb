function delCust(id) {
    if (confirm('是否确认删除？')) {
        window.location.href = '/customer.do?method=deleteCustomerById&id=' + id;
    }
}

function editCust(id) {
    window.location.href = '/customer.do?method=editCustomerById&id=' + id;
}

function page(pageNo) {
    window.location.href = "/customer.do?method=customerList&pageNo=" + pageNo;
}