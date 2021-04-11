const checkall = document.getElementById('checkall')
const elements = document.getElementsByClassName("checkbox")
const block = document.getElementById('block')
const unblock = document.getElementById('unblock')
const del = document.getElementById('delete')
const backUrl = 'http://127.0.0.1:8080/'

async function xhr(url, method, data) {
    const xhr = new XMLHttpRequest();
    xhr.open(method, backUrl + url, false, );
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(data);
}

function checkedRows(){
    let list = []
    for (let el of elements)
        if (el.checked)
            list.push(el.parentNode.nextSibling.nextSibling.firstChild.textContent)
    return list
}

checkall.addEventListener('change', () => {
    const switchTo = checkall.checked
    for (let el of elements)
        el.checked = switchTo
})

block.addEventListener('click', () => {
    let list = checkedRows()
    xhr('users/block', 'POST', JSON.stringify(list))
    window.location.reload();
})

unblock.addEventListener('click', () => {
    let list = checkedRows()
    xhr('users/unblock', 'POST', JSON.stringify(list))
    window.location.reload();
})

del.addEventListener('click', () => {
    let list = checkedRows()
    xhr('users/delete', 'DELETE', JSON.stringify(list))
    window.location.reload();
})

