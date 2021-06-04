# 요구사항
- [X] DTO 수정
- [X] LineResponse에 StationResponse 추가하기 
- [X] Section Entity 추가
    - [X] Station 2개 필요, Up, Down
- [X] Line에 Entity 매핑 및 연관관계 편의메서드 제공


# 요구사항
- [X] Section 추가하는 API 추가
  - [X] 예외케이스에 대한 API 추가
    - [X] 신규_구간_사이에_Section이_존재하면_안된다
    - [X] 이미 연결된 노선은 추가할 수 없음
      - [X] A<->B, B<->C => A<->B, B<->C A<->C 추가 불가능
    - [X] 상행역, 하행역 중 하나라도 노선에 포함이 안되어 있으면 불가능
  
# 요구사항
- [ ] Section에서 역을 제거하는 API 추가
  - [ ] 예외케이스에 대한 API 추가
    - [ ] 노선에 등록이 안되어있는 역을 제거하려 할 때
    - [ ] 노선에 구간이 하나밖에 없을때
  
1. A - B - C (A - B, B - C) 중 B를 지우면 B가 상행선인 것을 지우고 연결하자.
2. A - B - C (A - B, B - C) 중 A를 지우면 A가 마지막 상행선이니 A - B를 지운다.
3. A - B - C (A - B, B - C) 중 C를 지우면 C가 마지막 하행선이니 B - C를 지운다.
4. A - B - C - D (A - B, B - C, C -D) 중 C를 지우면 C가 상행선인 것을 지우고 연결하자 C - D 삭제 후 B - C => B - D로 변경