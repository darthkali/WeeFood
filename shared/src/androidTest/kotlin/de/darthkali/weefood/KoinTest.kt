package de.darthkali.weefood

//@RunWith(AndroidJUnit4::class)
//@Category(CheckModuleTest::class)
//class KoinTest : BaseTest() {
//
//    @Test
//    fun checkAllModules() {
//        initKoin(
//            module {
//                single<Context> { getApplicationContext<Application>() }
//                single { get<Context>().getSharedPreferences("TEST", Context.MODE_PRIVATE) }
//                single<AppInfo> { TestAppInfo }
//                single { {} }
//            }
//        ).checkModules {
//            create<Kermit> { parametersOf("TestTag") }
//        }
//    }
//
//    @AfterTest
//    fun breakdown() {
//        stopKoin()
//    }
//}