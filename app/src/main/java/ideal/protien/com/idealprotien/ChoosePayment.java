package ideal.protien.com.idealprotien;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import Model.Order_list;

public class ChoosePayment extends AppCompatActivity {
    Spinner sp_choosepayment;
  public static   int choosepaymentNum;
    Order_list getselecteditem;
    int totalprice,orderId;
    String[] paymentList = {"الدفع والإستلام من الشركة","الدفع والشحن اونلاين", "الدفع اونلاين والإستلام في الشركة", "الدفع عند الإستلام"  };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_payment);
       findViewsId();
        Intent iN = getIntent() ;
        //  ArrayList<Order_list>  DETAILS = (ArrayList<Order_list>)i.getSerializableExtra("orderlist");
        getselecteditem = (Order_list)iN.getSerializableExtra("orderlist");
//        Log.e("lisst",getselecteditem.toString());
       /* Orderlist = (ArrayList<Order_list>)iN.getSerializableExtra("orderlist");
        Log.e("orderlist",Orderlist+"");*/
        //payment = "2";
        totalprice = iN.getIntExtra("totalprice", 0);
        orderId = iN.getIntExtra("orderId",0);
        Log.e("total", totalprice + orderId+"");

    }


    private void findViewsId() {
        sp_choosepayment = (Spinner)findViewById(R.id.sp_choosepayment);
    }
}
