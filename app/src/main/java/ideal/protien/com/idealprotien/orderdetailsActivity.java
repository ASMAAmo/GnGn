package ideal.protien.com.idealprotien;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapter.CheckAdapter;
import Adapter.OrderdetailAdapter;
import Adapter.ordersAdapter;
import ConstantClasss.Constanturl;
import ConstantClasss.Idealinterface;
import ConstantClasss.Prefs;
import Model.Order_list;
import Model.OrderdetailsComponant;
import Model.OrderdetailsModel;
import Model.Ordersmodel;
import Model.Productdetails;
import cc.cloudist.acplibrary.ACProgressConstant;
import cc.cloudist.acplibrary.ACProgressFlower;
import retrofit.RestAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class orderdetailsActivity extends AppCompatActivity {
ListView list_ordersdetail;
    Order_list getselecteditem;
    List<OrderdetailsComponant> orderdetailsList;
    Prefs mypref;
    int orderid;
    ACProgressFlower dialog;
    Double AllFees;
     public static TextView txttotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderdetails);
        list_ordersdetail =(ListView)findViewById(R.id.list_ordersdetail);
        txttotal =(TextView)findViewById(R.id.txttotal);
        mypref = new Prefs();
        Intent intent = getIntent();
        orderdetailsList = new ArrayList<>();
        orderid = intent.getIntExtra("orderid",0);
       GetOrderDetails(orderid);
    //   AllFees = getTotal();
       // txttotal.setText(String.valueOf(AllFees));
    }
    public void GetOrderDetails(int orderid){
        dialog = new ACProgressFlower.Builder(orderdetailsActivity.this)
                .direction(ACProgressConstant.DIRECT_CLOCKWISE)
                .themeColor(Color.WHITE)
                .fadeColor(Color.DKGRAY).build();
        dialog.show();
        HashMap input = new HashMap();
        input.put("orderId", orderid);
        String Authorized_accesstoken = "Bearer" + " " + mypref.getDefaults("ACCESS_TOKEN", orderdetailsActivity.this);
        Log.e("aUTHORIZED", Authorized_accesstoken);
        Constanturl.createService(Idealinterface.class).GetOrderDetails(input, "application/json", Authorized_accesstoken).enqueue(new Callback<OrderdetailsModel>() {
            @Override
            public void onResponse(Call<OrderdetailsModel> call, Response<OrderdetailsModel> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                if (response.isSuccessful()) {
                    OrderdetailsModel ex = response.body();
                    orderdetailsList = ex.getResultdetailsorder().getOrderComponants();
                    OrderdetailAdapter adapter = new OrderdetailAdapter(orderdetailsActivity.this,orderdetailsList);
                    list_ordersdetail.setAdapter(adapter);
                } else {
                    response.code();
                    // Log.e("xxxx", "CCCC" + response.code());

                }
            }

            @Override
            public void onFailure(Call<OrderdetailsModel> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                // Log.e("ERROrss", "ASMAA");
            }

        });
    }

}
