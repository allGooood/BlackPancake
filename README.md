# BlackPancake.
#### 이전에 구현했던 일반 웹 애플리케이션 'PinkPancake'을 Restful API 서버로 바꾸는 프로젝트 입니다.

목표.
----------
* JWT를 통한 인가/인증 구현
* RESTful한 디자인의 URI로 자원 관리.
* JUnit과 MockMVC를 사용한 테스트.
* Github과 Jenkins를 연동한 빌드 자동화.
* Spring Boot, Spring Data JPA, MySQL 사용.

구성.
----------
![image](https://user-images.githubusercontent.com/71624066/163704066-256c65c3-1308-414c-8380-fca2f599018d.png)

요구사항.
----------
| 기능 | 구현 | 테스트 | 요구사항 |
| ------ | -- | -- |----------- |
| auth | ☑️ | ☑️ | JWT 관련 클래스 구현(Token Provider, Filter), 로그인 |
| users | ☑️ | ☑️ | 회원가입, 회원정보 조회/수정 |
| products | ☑️ | ☑️ | 상품목록, 상품 상세조회, 상품 검색 |
| carts | ☑️ |  | 장바구니 담기, 수량 변경, 상품 삭제 |
| orders |  |  | 상품 주문, 주문 취소, 주문 조회, 주문 상세조회 |
| paging | ☑️ | ☑️ | Pageable을 구현한 페이징 사용 |
| admin |  |  | 회원목록 조회(users), 상품 등록/수정/삭제(products)  |
| Build & Deploy |  |  | Jenkins와 EC2를 사용한 빌드, 배포 자동화  |

ERD.
----------
![blackpancakes](https://user-images.githubusercontent.com/71624066/164154318-b7bb1a80-0a51-4df1-a59c-789c5f68ec87.png)


변경 사항.
----------
* Test Class 세분화</br>
    Controller 테스트에서 Controller, Service, Repository 로 나눈 단위 테스트 작성.
* Test용 DB 분리

Tech Stack.
----------
* Spring Boot 2.6.3
* JDK 11
* H2(Test, InMemory) + MySQL(운영)
* Spring Data JPA
* Gradle
    
