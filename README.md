# What is “Sugar Road”?

<table>
    <tbody>
        <tr>
            <td>
            .
            </td>
            <td>
            .
            </td>
        </tr>
    </tbody>
</table>

## 라이브 사이트 임배드

배포 준비 중 😅

<aside>

## 프로젝트 소개
### 🍰 디저트 정보 공유 웹, **sugar road**
(`2023.10.4` ~ `2023.10.24` | **20 days**)

- **“디저트 정보를 공유하고 소통할 수 있는 커뮤니티가 없어!”** 디저트를 사랑하는 팀원이 불편함을 느끼고 제안하여 시작된 프로젝트입니다.
- sugar-road에서는 디저트 가게 정보를 손쉽게 찾아볼 수 있으며 커뮤니티에서 디저트를 사랑하는 사람들과 소통할 수 있습니다.
### 신경 쓴 점 😁

- **Mobile first** : 어디든 주변에 있는 디저트 가게를 볼 수 있도록
- **Kakao map API** : 가게 정보와 더불어 위치도 쉽게 확인할 수 있도록
- **로그인 최소화** : 사용자가 손쉽게 여러 기능을 사용할 수 있도록
- **전원 풀스택 개발** : 모든 팀원들이 풀스택 경험을 쌓을 수 있도록

</aside>

## 멋진 팀원들(+ `담당 기능`) 👏


![Untitled](readme_images/Untitled.png)

### [호준](https://github.com/papamoon0113) `PM`

`리뷰` `댓글` `추천`

![Untitled](readme_images/Untitled%201.png)

### [은기](https://github.com/Nohkii)

`가게` `메뉴`

![Untitled](readme_images/Untitled%202.png)

### [**승범**](https://github.com/sonsbe)

`게시글` `검색` `필터`

![Untitled](readme_images/Untitled%203.png)

### [해성](https://github.com/undercover0072)

`회원가입` `카카오맵`

## 마일스톤 🚩

![Untitled](readme_images/Untitled%204.png)

## 스택

<h3>Environment</h3>
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">
<img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white">
<img src="https://img.shields.io/badge/intellij idea-000000?style=for-the-badge&logo=intellijidea&logoColor=white">
<h3>Database</h3>
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
<h3>Backend</h3>
<img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<h3>Frontend</h3>
<img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
<img src="https://img.shields.io/badge/css3-1572B6?style=for-the-badge&logo=css3&logoColor=white">
<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white">
<h3>Communication</h3>
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

## 시작 가이드

### Requirements

- `Jdk 17` ([download](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html))
- `MySQL` ([download](https://dev.mysql.com/downloads/mysql/))
- `IntelliJ` ([download](https://www.jetbrains.com/idea/download/?section=windows)) : Optional, but recommended

### Installation

1️⃣ **Git clone**

```powershell
git clone https://github.com/papamoon0113/sugar-road.git
cd sugar-road
```

**2️⃣ Create table in MySQL**

```sql
# Log in to mysql first

source \database\create_all_table.sql
```

**3️⃣ Set *application.properties***

```sql
# path : sugar-road\server\src\main\resource\application.properties

spring.datasource.url: jdbc:mysql://[domain]:[port]/edudb?characterEncoding=UTF-8
# example
# spring.datasource.url: jdbc:mysql://localhost:3306/edudb?characterEncoding=UTF-8
spring.datasource.username: [username]
spring.datasource.password: [password]
spring.datasource.driver-class-name: com.mysql.cj.jdbc.Driver
```

4️⃣ Run server

```powershell
.\gradlew bootRun
```

### 관련 페이지 🖇️

[프로젝트 노션 페이지](https://www.notion.so/Sugar-Road-FIN-c144f480774644fd9f3fc77a69fd64a9?pvs=21)

[화면 디자인 (Figma)](https://www.figma.com/file/HVqTOUkPAM1YAoE4M9P4Z3/Untitled?type=design&node-id=103%3A592&mode=design&t=yjsby6PwjLPMKlYf-1)

[API 명세서](https://www.notion.so/API-7b6b86210a2d48e294fca029d0554d5e?pvs=21)

[ERD (Erdcloud)](https://www.erdcloud.com/d/cp8ny2z3DuMq93LP8)