# DersTakipSistemi
>**Uygulamanın Amacı:**
>Üniversitelerde derslerin devamsizlik bilgisi gec girildiğinden derslerle ilgili yoklama takibi öğrenciye düşüyor.Bu ugulama sayesinde yoklama takibi çok kolay.Uygulamayı kurduktan sonra [ders ekleme](https://github.com/bekir1184/DersTakipSistemi/new/master?readme=1#ders-ekleme) kısmından derslerinizin giriş çıkış saatlerini ve devamsizlik hakkını (saat olarak) girdikten sonra ders çıkışından 5 dakika sonra size [bildirim](https://github.com/bekir1184/DersTakipSistemi/blob/master/README.md#bildirim-ayarlama) geliyor ve derse girip girmediğiniz hakkında sizden bilgi istiyor ve bu sayede kac saat devamsizlik yaptığınızı hafızada tutuyor.


## Ana Sayfa
>Ana sayfada devamsizlik durumu ,yaklaşan dersler ve yaklaşan sınavlara ulaşmamızı sağlayan kısayol ekranları bulunuyor.
![Screenshot_20200229-015607](https://user-images.githubusercontent.com/47231687/75594764-e4e68f00-5a9a-11ea-804c-8b1b9f782a27.png)
>>>
## Ders Ekleme
>Ders ekleme kısmında ders adı ,devamsızlık hakkı ve dersin haftada kaç saat olduğunu gerekli alanlara doldurduktan sonra 
haftadaki ders sayısına gore karşımıza derslerin ayrı ayrı giriş çıkış saatlerini ve haftanın hangi gunu olduğunu belirtmemize yarıyan kısım karşımıza çıkıyor.

![Screenshot_20200229-015616](https://user-images.githubusercontent.com/47231687/75594849-4b6bad00-5a9b-11ea-928b-6ea63c702dd1.png)





**Bu alanları da doldurduktan sonra dersimiz ekleniyor ve daha sonra [programda](https://github.com/bekir1184/DersTakipSistemi/new/master?readme=1#ders-program%C4%B1) ve [devamsizlik durumda](https://github.com/bekir1184/DersTakipSistemi/new/master?readme=1#devamsizlik-durumu) gözüküyor.**

## Ders Programı 
>Eklenen dersler gunune gore programa ekleniyor 

![Screenshot_20200229-020009](https://user-images.githubusercontent.com/47231687/75594908-840b8680-5a9b-11ea-8679-0f82e65e9435.png)


## Devamsizlik Durumu
>İlgili derslerin devamsizlik durumlarının gorundugu alan .
![Screenshot_20200229-015953](https://user-images.githubusercontent.com/47231687/75594951-a7363600-5a9b-11ea-8a10-b13f5269440a.png)
![Screenshot_20200229-020002](https://user-images.githubusercontent.com/47231687/75594981-c208aa80-5a9b-11ea-9368-e87aa8f76515.png)


## Bildirim Ayarlama 

![Screenshot_20200229-015944 (copy)](https://user-images.githubusercontent.com/47231687/75595124-2deb1300-5a9c-11ea-957c-ab20ced78479.png)

>Bildirim gondermek için   belirli bir tarihi ayarlamak  gerekiyordu bu tarihi ayarlamak için AlarmManeger sınıfını kullandım.
Ders biligilerini bir Calender nesnesine aktardım ve startAlarm metoduna paremetre olarak gonderdim.
```java 
private void startAlarm(Calendar c,String dersAdi) {

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, Alarm.class);
        DersAdi.dersAdi=dersAdi;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);


        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }

```
Alarm Maneger sınıfı ayarlan zaman gelince bizi Alarm sınıfına gonderiyor.Alarm sınıfı BroadcastReceiver sınıfını extends ediyor.
```java
<receiver android:name=".Alarm"/>
```
Bu sayede uygulama kapalıyken bildirim gelmesini sağlıyor.
```java
public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        NotHelper notificationHelper = new NotHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        notificationHelper.getManager().notify(1, nb.build());
    }
```
Alarm sınıfıda bizim oluşturduğumuz NotHelper sınıfından bir nesne oluşturuyor ve NotHelper sınıfında ayarladığımız bildirim telefona düşüyor.

NotHelper sınıfında ayarladığımız bildirim.
```java
 return new NotificationCompat.Builder(getApplicationContext(), channelID)
                .setContentTitle(DersAdi.dersAdi)
                .setContentText("Derse girdin mi ?")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.ic_class_black_24dp);
                
```



