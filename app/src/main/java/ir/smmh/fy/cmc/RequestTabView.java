// package ir.smmh.fy.cmc;
//
// import android.content.Context;
// import android.widget.ArrayAdapter;
// import android.widget.AutoCompleteTextView;
// import android.widget.Spinner;
//
// import org.json.JSONArray;
// import org.json.JSONObject;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import ir.smmh.fy.Util;
// import ir.smmh.fy.code.TabView;
//
// public class RequestTabView extends TabView {
//     public RequestTabView(final Context context) {
//         super(context);
//
//
//         final CharSequence[] endpoints = {};
//         final Spinner endpointPicker = new Spinner(context);
//         final ArrayAdapter<CharSequence> endpointPickerAdapter =
//             new ArrayAdapter<CharSequence>(context, android.R.layout.simple_spinner_item);
//         e.setAdapter(a);
//
//     }
//
//     void req() {
//         Map<String, String> params = new HashMap<>();
//         params.put("convert", target.symbol);
//         params.put("start", "1");
//         params.put("limit", Integer.toString(n));
//         // percent_change_24h_min TODO
//         CoinMarketCap.request("/v1/cryptocurrency/listings/latest", params, (JSONArray data) -> {
//             for (JSONObject obj : Util.over(Util.JSONArray.iterate(data))) {
//                 addRow(obj);
//             }
//         }, null);
//     }
// }
