// const addBtn = document.querySelector('.add');
// var i = 1;
//
// addBtn.onclick = () => {
//     // Создаем блок и добавляем номер
//     const createBlock = document.createElement('div');
//     createBlock.className = 'form-group';
//     createBlock.innerHTML = ' <div class="form-group">\n' +
//         '                <label class="form-control-label">Добавить кандидата</label>\n' +
//         '                <div class="row">\n' +
//         '                    <div class="col-6">\n' +
//         '                        <input type="text" class="form-control" name="candidate[]">\n' +
//         '                    </div>\n' +
//         '                    <div class="col-6">\n' +
//         '                        <a class="add_candidate delete" onclick="delNode(this)">-</a>\n' +
//         '                    </div>\n' +
//         '                </div>\n' +
//         '            </div>'
//     addBtn.insertAdjacentElement('beforebegin', createBlock);
//     i++;
// }
//
//
// function delNode(el){el.parentNode.parentNode.parentNode.parentNode.remove()}
//
//

const links = document.querySelectorAll('.select_a');
const blocks = document.querySelectorAll('.select_block');

links.forEach((link) => {
    link.addEventListener('click', (event) => {
        event.preventDefault();
        const targetId = link.getAttribute('href').substring(1);
        const targetBlock = document.getElementById(targetId);
        blocks.forEach((block) => {
            block.classList.add('hidden');
            block.classList.remove('show_blocks')
        });
        targetBlock.classList.remove('hidden');
        targetBlock.classList.add('show_blocks');
    });
});

blocks.forEach((block) => {
    block.classList.add('hidden');
});

