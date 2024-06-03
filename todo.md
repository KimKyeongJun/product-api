# 보완해 볼 것

- ResponseEntity 사용 고민
  - 정상 응답일 때는 기존 ApiResponse를 사용하고, 예외 발생 시에는 ResponseEntity를 사용하는 것이 좋을까?
- 멀티 모듈 다시 사용해 보기
  - 기존 목표였던 도메인 레이어를 떼어내서 별도의 모듈로 만들어보기
- 테스트 코드 마무리 작성
  - 현재 ProductService에만 테스트 코드를 작성했는데, 다른 Service, Controller에 대한 테스트 코드도 작성
- 응답 포맷은 그대로 유지하고 DB를 조금 더 확장해서 API 수정해보기
- SonarLint 사용해서 코드 리팩토링(취약점 제거)