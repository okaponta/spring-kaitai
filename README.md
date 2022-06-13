# spring-kaitai

Spring解体新書第二版をhands onでやりました。  
Spring/SpringBootはやっていた当時の最新バージョンを使っているため、  
本のサンプルコードと異なるコードになっている部分があります。

# バージョン
- Java OpenJDK17
- SpringBoot v2.7.0

# バージョンアップ起因で変更した点

## 初期化時のSQLの指定方法

バージョンアップにより、[application.properties](https://github.com/okaponta/spring-kaitai/blob/main/spring-boot-sample/src/main/resources/application.properties#L5-L8)の記述方法が変わっています。  

詳しくは以下に書いています。

 - https://qiita.com/okaponta_/items/41202cf385de1fe4c379

## Recordの使用

java16から正式リリースされたRecordを使用して一部のクラスを記述しています。  
具体的には以下の2クラス
 - [SignupForm](https://github.com/okaponta/spring-kaitai/blob/main/spring-boot-sample/src/main/java/com/example/form/SignupForm.java)
 - [UserDetailForm](https://github.com/okaponta/spring-kaitai/blob/main/spring-boot-sample/src/main/java/com/example/form/UserDetailForm.java)

これに伴い、`ModelMapper`の設定を一部変えています。
 - [JavaConfig](https://github.com/okaponta/spring-kaitai/blob/main/spring-boot-sample/src/main/java/com/example/config/JavaConfig.java#L13-L15)

詳しくは以下にまとめています。
 - https://qiita.com/okaponta_/items/924b48385ce1939ddeaf

## SecurityConfigの書き方変更

SpringSecurity5.7から`WebSecurityConfigurerAdapter`がdeprecatedになって、本で記載されている書き方が非推奨となりました。  
これで、[SecurityConfig](https://github.com/okaponta/spring-kaitai/blob/main/spring-boot-sample/src/main/java/com/example/config/SecurityConfig.java)を書き直しています。

大きな変更点は以下
 - `WebSecurityConfigurerAdapter`の継承をやめた
 - `configure(WebSecurity)` -> `WebSecurityCustomizer`のBean登録
 - `configure(HttpSecurity)` -> `SecurityFilterChain`のBean登録
 - `configure(AuthenticationManagerBuilder)` -> `InMemoryUserDetailsManager`のBean登録(インメモリの場合)
 - `configure(AuthenticationManagerBuilder)` -> `UserDetailsManager`のBean登録
   - 上記に伴い不要となった`UserDetailsServiceImpl`の削除
   - 以下の記事にまとめています
     - https://qiita.com/okaponta_/items/de1e640037b89b3ad6ca

# やってたときのログ(Zenn)
https://zenn.dev/okaponta/scraps/97f0e25eeecd36
