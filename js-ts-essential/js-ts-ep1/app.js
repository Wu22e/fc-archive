// ajax 를 가지고 XMLHttpRequest가 제공하는 도구를 사용할 수 있게 됨.
const ajax = new XMLHttpRequest();

// 데이터가 바뀔 수 있는 주소, 데이터 페이지 같은 것들은 변수로 빼두는게 좋음.
const NEWS_URL = 'http://api.hnpwa.com/v0/news/1.json';

// 해커 뉴스 사이트에서 데이터를 동기적으로 가져온다. (3번째 인자가 async 여부임)
// open 만 한다고 해서 데이터를 가져오는 것은 아님.
ajax.open('GET', NEWS_URL, false);

// send 함수를 호출하면 데이터를 가져옴.
ajax.send();

// 데이터는 ajax의 response 라고 하는 값에 들어 있음.
// console.log 를 찍고 html 을 띄어서 개발 도구를 살펴보면 콘솔 탭에 해커뉴스 데이터가 쭉 찍히는 것을 볼 수 있음.
console.log(ajax.response);

// response 데이터를 parse 하여 객체 형식으로 변환한다.
const newsFeed = JSON.parse(ajax.response);
// console.log 를 찍고 html 을 띄어서 개발 도구를 살펴보면 콘솔 탭에 해커뉴스 데이터가 preview 형식으로 쭉 찍히는 것을 볼 수 있음.
console.log(newsFeed);

// document 라는 도구가 HTML을 조작하는 데 필요한 모든 도구를 제공한다.
const ul = document.createElement('ul');

// newsFeed 는 배열 형식임.

for(let i = 0; i < 10; i++) {
    const li = document.createElement('li');
    li.innerHTML = newsFeed[i].title;
    ul.appendChild(li);
}

// div 태그 하위에 자식 요소라는 표현이다.
document.getElementById('root').appendChild(ul)
