class Review extends HTMLElement {
    constructor(){
        super();
        this.storeId = this.getAttribute("data-storeId");
        console.log(`this.storeId : ${this.storeId}`);

        this.render();
    }
    render(){
        fetch(`/review?storeId=${this.storeId}`)
          .then(response => {return response.json();})
          .then(json => this.readJson(json));
    }
    readJson(json){
      console.log(json);
      Object.values(json).forEach((r) => {
        let newReview = document.createElement("div");
        newReview.appendChild(document.createTextNode(r['reviewId'] + "/"));
        newReview.appendChild(document.createTextNode(r['userId'] + "/"));
        newReview.appendChild(document.createTextNode(r['content'] + "/"));
        newReview.appendChild(document.createTextNode(r['postedDate'] + "/"));
        newReview.appendChild(document.createTextNode(r['star'] + "/"));
        this.appendChilde(newReview);
      })
    }
  }

customElements.define("custom-review", Review);

//<custom-review th:data-storeId=${storeDTO.storeId}></custom-review>
//<script src="/js/review.js"></script>

// export default Recommendation;