# JBreakout
「JBreakout」はJavaで書かれたBreakout（ブロック崩し）です。

# コンパイル方法
## Step1
```
$ git clone https://github.com/mpp0xdd/JGLib.git
$ git clone https://github.com/mpp0xdd/JBreakout.git
$ cd JBreakout
```
## Step2
### Linux
```
$ javac -d classes -encoding UTF-8 -cp ../JGLib:. *.java
```
### Windows
```
$ javac -d classes -encoding UTF-8 -cp ../JGLib;. *.java
```

# 実行方法
```
$ java -cp classes JBreakout
```

# 効果音とBGMを鳴らすには
サウンド・ファイルを用意し，classes/soundsフォルダに配置してください。
- ball_bounce.wav
  - ボールがバウンドする時の音
- ball_fall.wav
  - ボールが画面の範囲外に落下した時の音
- bgm.wav
  - BGM
- paddle_rebound.wav
  - パドルがボールを跳ね返す時の音
- brick_eliminate.wav
  - ブロックが壊れる時の音

# jarファイルの作成と実行
## Step1
### Linux
```
$ chmod u+x makejar.sh
```
### Windows
```
$ REM No Operation
```
## Step2
### Linux
```
$ ./tools/makejar.sh
```
### Windows
```
$ tools\makejar.bat
```
## Step3
```
$ java -jar JBreakout.jar
```
