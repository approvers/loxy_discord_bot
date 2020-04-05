# my_fancy_discord
Looking for a bot that meowed due to responding to all channels?
You must be lucky, that's this bot! 

## i wanna do
- scripting
- that's it

### Tips
- This bot has meown once because this responded to all channels, and made the server chaos.
- I failed making repo once, so this repo is 2nd generation

### Current 技術的仕様
以下のファイルに分かれています
- `.../src/main/kotlin/`
  - `main.kt`
    実行ファイルです
  - `MyFancyDiscordClient.kt`
    クライアントそのものです
  - `lib/`
    - `CommandExecutor.kt`
      コマンドを司ります
    - `Utility.kt`
      便利関数たちです
    - `respond/`
      - `CommandResultEnum.kt`
        コマンドの実行結果を示すenumたちです
      - `Embeddable.kt`
        オブジェクトが`embed`としてメッセージ送信できるようにするインターフェースです
      - `RespondMessage.kt`
          コマンドが出力したメッセージと実行結果を保持するクラスです
    - `cmd_impl/`
      - `Command.kt`
        コマンドとして実行できるようにするインターフェースです
      - `ExampleCommand.kt`
        `try`コマンドを実装しているところです