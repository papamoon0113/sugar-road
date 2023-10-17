class Comment extends HTMLDivElement{
    constructor(){
        super();
        this.referenceType = this.getAttribute("data-referenceType");
        this.referenceId = this.getAttribute("data-referenceId");
        this.userId = this.getAttribute("data-userId");
        this.nickname = this.getAttribute("data-nickname");
        this.content = this.getAttribute("data-content");

        this.render();
    }

    render(){
        // fetch
    }
}