# NumPercentView
A boring Number Percent View, Feel Free To Use It If You Need.

##1. In layout xml.
    <com.example.dw.numpercentview.widget.NumPercentView
        android:id="@+id/numpercent"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerHorizontal="true"
        android:layout_centerVertical="true"
        android:paddingBottom="5dp"
        android:paddingLeft="20dp"
        android:paddingRight="20dp"
        android:paddingTop="5dp"
        custom:backgroundColor="#ffffff"
        custom:percentColor="@color/colorAccent"
        custom:currentValue="200"
        custom:stripColor="#000000"
        custom:textSize="14sp"
        custom:totalValue="1500" />
        
##2. You can change the total number and current number.
        numPercentView = (NumPercentView) findViewById(R.id.numpercent);
        numPercentView.setCurrentValue(500);
        numPercentView.setTotalValue(1000);
        
##3. Here is a ScreenShot.

![](/ScreenShot/Screenshot_2016-10-13-16-04-42.png "ScreenShot")
