# apiServer1
api 명세서
https://documenter.getpostman.com/view/27924557/2s93z9ci6y

의문점
1. 로그인할때 생긴 pwt가 header에 자동으로 들어가지 않아서 다른API를 요청할때마다 Header에 pwt를 수동으로 넣어줘야 했다.
   질문 - 필터에서 자동으로 들어가게 설정 해줘야 하나요?
2. post entitiy의 userid와 user entity의 id가 일치하지 않으면 오류메세지 라는 코드를 작성하고 싶었지만 잘 안됐다. usernmae으로 일치여부를 생성하는건 성공했다.
   post entity에
   public void checkUsername(String username) {
        if(!this.username.equals(username)) {
            throw new IllegalArgumentException("작성자가 일치하지 않습니다.");
        }
    }
   이렇게 넣어서 체크를 했는데 id로 체크하ㄹㄹ는 방법을 다시 시도해 봐야겠다.
   
