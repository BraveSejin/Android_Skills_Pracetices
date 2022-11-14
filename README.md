# Android_Skills_Pracetices

안드로이드 스킬 연습용 레포지토리입니다.

작성 방법
1. 새로운 프로젝트를 하위 폴더에 생성한다.
2. README.md 파일의 '프로젝트 목록'애 해당 프로젝트에 대한 개요와 사용한 기술을 기재한다.

## 프로젝트 목록
### 1. TODO앱
TODO를 추가 및 삭제할 수 있는 어플리케이션입니다.
사용 기술: Koin, Junit, State Pattern, Clean Architecture

요약
1. data, domain, presentation 패키지로 나누고, TDD를 통해 비즈니스 로직을 먼저 작성한 후 UI를 구현하였음. 
비즈니스 로직을 담당하는 레이어가 먼저 구현되어 있으므로 UI개발시에 상대적으로 비즈니스 로직에 신경을 좀 덜 써도 되어서 간단헀음.
2. 처음 테스트 케이스를 작성할 때와 UI구현시에 repository의 인터페이스가 조금 달라지고 State도 한두개가 추가되었음. 렉처를 따라가면서 했는 데에도 이 정도라면 테스트케이스 작성이 쉬운 일은 아닌 것 같다. 추가로 테스트에 관한 rule 등을 미리 정의해야 하고, LiveData의 경우 따로 Observable 클래스를 이용해서 테스트하는 클래스를 작성해서 간접적으로 테스트해야 했다. 이와 관련하여 LiveData를 직접 테스트할 수 있는 방법이 독스에 있던 것 같은데 찾아봐야 할 것 같다.
3. DI로 Koin을 사용했는데 서비스 로케이터와 DI의 차이점을 좀 더 정리해볼 것.

참조 : https://github.com/Fastcampus-Android-Lecture-Project-2021/aop-part5-chapter01