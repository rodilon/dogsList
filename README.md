# dogsList - idwall

apk = [apk](https://github.com/rodilon/dogsList/blob/master/idwall-dogs.apk)

## Arquitetura
* Arquitetura MVVM
* LiveDatas
* Unit Tests e Ui Tests

## Libs utilizadas
* [Picasso](https://square.github.io/picasso/): Para carregamento e cache automático de imagens
* [Retrofit](https://square.github.io/retrofit/): Para requisições HTTP
* [Mockk](https://mockk.io/): Para mocks nos testes unitários e instrumentados
* [Hawk](https://github.com/orhanobut/hawk): Para criptografia do token, assim realizando login transparente
* [GMS](https://developers.google.com/android/reference/packages): Para validar a versão do Play Services em versões antigas do android.
* [PhotoView](https://github.com/chrisbanes/PhotoView): Para realizar o zoom no click da imagem de dogs.
* [Truth](https://github.com/google/truth): Para asserts dos testes unitários.
* [Espresso](https://developer.android.com/training/testing/espresso): Para testes de ui
