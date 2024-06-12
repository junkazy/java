# 24.3
  * 마스킹 솔루션
    1. 마스킹
       - INPUT : Scanner (MASK EMP_INFO.TXT)
       - OUTPUT : Console (L10010 K*Y 010-1*34-5*78)
       - 원본 파일을 읽어서 콘솔에 암호화 데이터 및 매핑 데이터를 출력함.

    2. 마스킹 및 언마스킹
        - INPUT : Scanner (MASK EMP_INFO.TXT , UNMASK L10010)
        - OUTPUT : Console (L10010 KKY 010-1234-5678) , File (MASK.TXT, MAP.TXT, LOG.TXT)
        - 마스킹 : 1번과 동일하나 콘솔 대신 파일로 출력함.
        - 언마스킹 : ID의 데이터를 MASK.TXT, MAP.TXT 파일에서 찾아서 조합하여 콘솔 출력 및 파일로 로깅함.

    2. 마스킹 및 언마스킹(HTTP)
        - INBOUND
          - INPUT : Http(post) - 마스킹 및 언마스킹 요청
          - OUTPUT : Http(post) - 정상 응답 및 복호화 데이터
        - OUTBOUND
            - OUTPUT : Http(post) - 매핑 데이터 적재요청(save) 및 매핑데이터 요청(retrieve)
            - INPUT : Http(post) - 정상 응답 및 매핑 데이터
        - 마스킹 : MASK.TXT는 2번과 동일하나 MAP.TXT 내용을 http로 요청함
        - 언마스킹 : ID의 데이터를 MASK.TXT 파일 및 http 매핑데이터 응답에서 조합하여 http 응답 및 파일로 로깅함

  * 보강 : 
    * POST Request/Respose 예제
    * File write 시 Create/Append 취사선택 예시
    * Gson : json type 변환 예시

