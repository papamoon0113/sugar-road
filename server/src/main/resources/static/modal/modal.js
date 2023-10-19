// modal.js
const modal = {
    open: function (message, onConfirm) {
        const modalElement = document.createElement('div');
        modalElement.innerHTML = `
            <div id="modal" style="display: block; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background: rgba(0, 0, 0, 0.7);">
                <div id="modal-content" style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); background: white; padding: 20px;">
                    <p>${message}</p>
                    <button id="modal-confirm">확인</button>
                    <button id="modal-close">취소</button>
                </div>
            </div>
        `;

        // 모달 창을 body에 추가
        document.body.appendChild(modalElement);

        // 확인 버튼 클릭 이벤트
        const confirmButton = document.getElementById('modal-confirm');
        confirmButton.addEventListener('click', function () {
            onConfirm();
            modal.close();
        });

        // 취소 버튼 클릭 이벤트
        const closeButton = document.getElementById('modal-close');
        closeButton.addEventListener('click', modal.close);
    },
    close: function () {
        const modalElement = document.getElementById('modal');
        if (modalElement) {
            modalElement.remove();
        }
    },
};

export default modal;
