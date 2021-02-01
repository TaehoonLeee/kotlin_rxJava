#Trending Movie List
> TMDB API를 이용한 trending movie list application
- Retrofit, Moshi, RxJava, Hilt, Dagger2, Paging Library, Glide를 사용하여 제작한 어플입니다.  
- TMDB에서 뽑아낸 Trending Movie List와 영화 정보, 영화 캐스팅, 캐스팅 배우 정보를 보여줍니다.  
![스크린샷 2021-02-01 오후 4 41 08](https://user-images.githubusercontent.com/48707020/106429226-821d2500-64ad-11eb-8c47-7b115da9c80d.png)  

## 목차  
- [사용 라이브러리](#사용-라이브러리)  
- [세부 기능](#세부-기능)  
        - [영화 목록](#영화-목록)  
        - [에러 처리](#에러-처리)  
        - [영화 세부정보](#영화-세부정보)  
        - [배우 세부 정보](#배우-세부-정보)  

## 사용 라이브러리  
> Retrofit(2.9.0), Moshi(1.11.0), Hilt(2.28-alpha), Glide(4.11.0), Paging(3.0.0-alpha11)  

## 세부 기능
### 영화 목록 
![스크린샷 2021-02-01 오후 4 41 08](https://user-images.githubusercontent.com/48707020/106429226-821d2500-64ad-11eb-8c47-7b115da9c80d.png)  
- Trending Movie의 리스트를 보여줍니다.    

### 에러 처리  
![스크린샷 2021-02-01 오후 4 41 45](https://user-images.githubusercontent.com/48707020/106429234-847f7f00-64ad-11eb-9468-92a154ab759b.png)
![스크린샷 2021-02-01 오후 4 42 32](https://user-images.githubusercontent.com/48707020/106429238-85181580-64ad-11eb-80c1-a54a0affd987.png)  
- LoadStateFooter와 SwipeRefreshLayout을 통해 영화 목록을 가져오는 데 실패할 경우 에러를 띄웁니다.  
        
### 영화 세부정보  
![스크린샷 2021-02-01 오후 4 43 21](https://user-images.githubusercontent.com/48707020/106429240-85b0ac00-64ad-11eb-8603-706d79728b79.png)  
- 메인 화면의 영화를 클릭 시 영화 세부 정보를 보여주는 페이지로 넘어옵니다.  
- 캐스팅된 배우들을 볼 수 있으며 배우 사진 클릭 시 배우의 세부 정보 페이지로 넘어갑니다.  

### 배우 세부 정보  
![스크린샷 2021-02-01 오후 4 43 44](https://user-images.githubusercontent.com/48707020/106429244-86494280-64ad-11eb-8c44-9537fd0dc72a.png)
![스크린샷 2021-02-01 오후 4 43 52](https://user-images.githubusercontent.com/48707020/106429245-86494280-64ad-11eb-8f5a-443710618479.png)  
- 배우 세부 정보를 보여줍니다.  
- viewPager2를 이용해 배우의 정보와 사진을 보여주는 Fragment를 관리합니다.  
