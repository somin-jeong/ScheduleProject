# API 명세서
https://held-gas-bf2.notion.site/API-1c0a43069a5580938d9efa5b21c14664?pvs=4

# ERD
![img.png](img.png)

# 예외처리
- @valid와 @NotNull, @NotBlank를 통해 필수 요청 값에 대한 null 검증 
- @valid와 @Size(max = 200)를 통해 할일이 최대 200자를 초과하는지 검증
- @valid와 @Size(max = 10)를 통해 할일 제목이 최대 10자를 초과하는지 검증
- @valid와 @Pattern을 통해 날짜 형식이 yyyy-MM-dd인지 검증
- @valid와 @Size(max = 4)를 통해 사용자 이름이 최대 4자를 초과하는지 검증
- @valid와 @Pattern을 통해 이메일 형식 검증

- 요청 파라미터 타입 검증
  - {
    "code": 2001,
    "status": 400,
    "message": "잘못된 파라미터 타입입니다."
    }

- 일정 상세 조회, 수정, 삭제 시 존재하지 않는 일정인지 검증
  - {
    "code": 4001,
    "status": 400,
    "message": "일정이 존재하지 않습니다."
    }
- 일정 수정, 삭제 시 비밀번호 일치하는지 검증
  - {
    "code": 4002,
    "status": 400,
    "message": "비밀번호가 일치하지 않습니다."
    }
- 일정 수정에 실패했을 때
  - {
    "code": 4003,
    "status": 500,
    "message": "일정 수정에 실패했습니다."
    }
- 일정 삭제에 실패했을 때
  - {
    "code": 4004,
    "status": 500,
    "message": "일정 삭제에 실패했습니다."
    }
- 일정 작성자와 수정하려는 유저가 일지하는지 검증
  - {
    "code": 4005,
    "status": 400,
    "message": "일정 작성자만 일정을 수정할 수 있습니다."
    }
- 일정 작성자와 삭제하려는 유저가 일지하는지 검증
  - {
    "code": 4006,
    "status": 400,
    "message": "일정 작성자만 일정을 삭제할 수 있습니다."
    }

- 로그인 시 사용자 이메일이 존재하는지 검증
  - {
    "code": 3001,
    "status": 400,
    "message": "존재하지 않는 이메일입니다."
    }
- 로그인 시 비밀번호가 올바른지 검증
  - {
    "code": 3002,
    "status": 400,
    "message": "비밀번호가 올바르지 않습니다."
    }
- 세션을 통해 userId 값 받을 때 존재하지 않는 사용자인지 검증
  - {
    "code": 3003,
    "status": 400,
    "message": "존재하지 않는 사용자입니다."
    }
- 사용자 정보 수정에 실패했을 때
  - {
    "code": 3004,
    "status": 500,
    "message": "사용자 정보 수정에 실패했습니다."
    }
- 사용자 삭제에 실패했을 때
  - {
    "code": 3005,
    "status": 500,
    "message": "사용자 삭제에 실패했습니다."
    }

- 댓글 수정, 삭제 시 존재하지 않는 댓글인지 검증
  - {
    "code": 5001,
    "status": 400,
    "message": "댓글이 존재하지 않습니다."
    } 
- 댓글 수정에 실패했을 때
  - {
    "code": 5002,
    "status": 500,
    "message": "댓글 수정에 실패했습니다."
    }
- 댓글 삭제에 실패했을 때
  - {
    "code": 5003,
    "status": 500,
    "message": "댓글 삭제에 실패했습니다."
    }
- 댓글 작성자와 수정하려는 유저가 일지하는지 검증
  - {
    "code": 5004,
    "status": 400,
    "message": "댓글 작성자만 댓글을 수정할 수 있습니다."
    }
- - 댓글 작성자와 삭제하려는 유저가 일지하는지 검증
- {
  "code": 5005,
  "status": 400,
  "message": "댓글 작성자만 댓글을 삭제할 수 있습니다."
  }

# 개발환경
- Framword: Spring Boot 3.4.4
- Language: Java 17
- Build: Gradle
- Database: MySQL 8.0.28
- ORM: Spring Data JPA
- Authentication: Session 기반 인증
- IDE: IntelliJ IDEA