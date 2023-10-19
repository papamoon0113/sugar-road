let commentInput = document.querySelector("#commentInput");
let referenceTypeSave;
let referenceIdSave;
let focusComment;

commentInput.requestOptions = {};
commentInput.requestOptions.body = {};

document.querySelector(".content").classList.add("comment-content");

class Comment extends HTMLDivElement{
    constructor(){
        super();
        this.referenceType = this.getAttribute("data-referenceType");
        this.referenceId = this.getAttribute("data-referenceId");
        this.loginId = this.getAttribute("data-loginId");

        referenceTypeSave = this.referenceType;
        referenceIdSave = this.referenceId;

        console.log(`this.referenceType : ${this.referenceType} this.referenceId : ${this.referenceId} this.loginId : ${this.loginId}`)

        this.render();
    }

    render(){
        this.innerHTML = "";
        fetch(`/comment/${this.referenceType}?id=${this.referenceId}`)
        .then(response => {return response.json();})
        .then(json => this.readJson(json));
    }

    readJson(json){
        Object.values(json).forEach(
            (comment) => {
                console.log(comment);
                this.innerHTML += `

                    <table class="v-table ${comment["parentComment"]?"child-comment":"comment"} ${comment["userId"] === this.loginId?"pink1":""}" id = "c${comment[this.referenceType + "CommentId"]}">
                      <tr>
                          <td class = "v-table-half t5">
                                ${comment["nickname"]}
                          </td>
                          <td class = ".v-table-eight">
                          </td>
                          <td class = ".v-table-eight">
                          </td>
                          <td class = "v-table-quater t6 right">
                                ${comment["postedDate"].substring(0, 10)}
                          </td>
                      </tr>
                      <tr>
                          <td colspan="4" class = "t6 v-item" id = "c${comment[this.referenceType + "CommentId"] + "-content"}">${comment["content"]}</td>
                      </tr>
                      <tr>
                          <td>
                            <button is = "custom-recommendation" class = "no-border" style = "background-color : transparent;" data-referenceType=${this.referenceType === 'post'?"C":"M"} data-referenceId="${comment[this.referenceType + "CommentId"]}"></button>
                          </td>
                          <td class = "t6">
                                ${comment["parentComment"]?"":"<a onclick = 'setParent("+ comment[this.referenceType + "CommentId"] +");' class = 't-lightgray'>답글</a>"}
                          </td>
                          <td class = "t6">
                                ${comment["userId"] === this.loginId?"<a onclick = 'editComment(" + comment[this.referenceType + "CommentId"] + ");' class = 't-lightgray'>수정</a>":""}
                          </td>
                          <td class = "t6">
                                ${comment["userId"] === this.loginId?"<a onclick = 'deleteComment(" + comment[this.referenceType + "CommentId"] + ");' class = 't-lightgray'>삭제</a>":""}
                          </td>
                      </tr>
                  </table>

                `;
            }
        );
    }
}

customElements.define("custom-comment", Comment, {extends:"div"});

function checkFocus(id){
    return focusComment == document.querySelector("#c" + id);
}

function colorFocus(id){
    (focusComment != undefined)&&(focusComment.classList.remove("pink2"));
    if(checkFocus(id)){
        focusComment = undefined;
        return true
    }
    focusComment = document.querySelector("#c" + id);
    focusComment.classList.add("pink2");
    return false
}

function setParent(id){
    console.log(commentInput.requestOptions.body["parentComment"]);
    if (!colorFocus(id)){
        commentInput.requestOptions.body.parentComment = id;
        commentInput.focus();
    }
    else {
        delete commentInput.requestOptions.body.parentComment;
    }
}

function editComment(id){
    if (!colorFocus(id)){
        let content = document.querySelector(`#c${id}-content`);
        commentInput.value = content.textContent;
        commentInput.requestOptions.body[`${referenceTypeSave}CommentId`] = id;
        commentInput.focus();
    }
    else {
        commentInput.setAttribute("value", "");
        delete commentInput.requestOptions.body[`${referenceTypeSave}CommentId`];
    }
}

function deleteComment(id){
    fetch(`/comment/${referenceTypeSave}/delete?id=${id}`)
    .then(() => {
        document.querySelector("#comment-area").render();
    });
}

function writeComment(){
    commentInput.requestOptions.headers = new Headers({"Content-Type" : "application/json"});
    commentInput.requestOptions.method = "POST";
    commentInput.requestOptions.body[`${referenceTypeSave}Id`] = referenceIdSave;
    commentInput.requestOptions.body.content = commentInput.value;
    let api = `/comment/${referenceTypeSave}/${commentInput.requestOptions.body["parentComment"]?"child/":""}${commentInput.requestOptions.body[referenceTypeSave + "CommentId"]?"edit":"write"}`

    console.log(commentInput.requestOptions);

    commentInput.requestOptions.body = JSON.stringify(commentInput.requestOptions.body);

    fetch(api, commentInput.requestOptions)
    .then(() => {
        document.querySelector("#comment-area").render();
        commentInput.value = "";
        commentInput.requestOptions = {};
        commentInput.requestOptions.body = {};
    });

}
// 답글이면 색깔 바꾸고 값 변경하는 기능

// 수정 삭제 버튼 누르면 작동하는 기능

// 전송 보내면 comment 영역 다시 render 