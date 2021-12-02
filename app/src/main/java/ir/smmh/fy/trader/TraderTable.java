// package ir.smmh.fy.trader;
//
// import android.content.Context;
// import android.widget.TableLayout;
// import android.widget.TableRow;
//
// import org.json.JSONArray;
// import org.json.JSONException;
// import org.json.JSONObject;
//
// import java.util.HashMap;
// import java.util.Map;
//
// import ir.smmh.fy.Util;
// import ir.smmh.fy.cmc.CoinMarketCap;
// import ir.smmh.fy.cmc.Crypto;
//
// public class TraderTable {
//
//     private double budgetUSD = 1000;
//     public final Crypto target;
//     public final TableLayout table;
//
//     private static class Row {
//
//         final Crypto src;
//         final double budget;
//         final Map<Crypto, Double> valueIn = new HashMap<>();
//
//         Row(final Crypto src, final double budget) {
//             this.src = src;
//             this.budget = budget;
//         }
//
//         public void setValueIn(Crypto in, Double value) {
//             valueIn.put(in, value);
//         }
//
//         public Double getValueIn(Crypto in) {
//             return valueIn.get(in);
//         }
//
//         TableRow visualize(Context context) {
//             final TableRow row = new TableRow(context);
//             row.addView(Util.textViewOf(context, src.symbol));
//             row.addView(Util.textViewOf(context, valueInUSD));
//             double b = budget / getValueIn(Fiat.USD);
//             row.addView(Util.textViewOf(context, valueInTarget));
//             row.addView(Util.textViewOf(context, b));
//             row.addView(Util.textViewOf(context, b / valueInTarget));
//             return row;
//         }
//     }
//
//     public TraderTable(Context context, Crypto target) {
//
//         this.target = target;
//
//         // create the table
//         table = new TableLayout(context);
//
//         // THANKS_TO: https://stackoverflow.com/a/25383106/
//         for (int i = 0; i < 5; i++) {
//             table.setColumnStretchable(i, true);
//         }
//         table.setStretchAllColumns(true);
//
//         // add styles
//         table.setDividerPadding(Util.dipToPixel(32));
//         // table.setcolor();
//
//         // add the header row
//         TableRow h = new TableRow(context);
//         h.addView(Util.textViewOf(context, ""));
//         h.addView(Util.textViewOf(context, "$"));
//         h.addView(Util.textViewOf(context, target.symbol));
//         h.addView(Util.textViewOf(context, "Budget"));
//         h.addView(Util.textViewOf(context, "Amount"));
//         table.addView(h);
//     }
//
//     private void addRow(JSONObject obj) {
//         try {
//             final Crypto src = CoinMarketCap.find(obj);
//             final JSONObject q = obj.getJSONObject("quote");
//             // final JSONObject u = q.getJSONObject("USD");
//             final JSONObject t = q.getJSONObject(target.symbol);
//
//             table.addView(row);
//             table.invalidate();
//
//         } catch (JSONException ignored) {
//         }
//     }
//
//     public void addRows(int n) {
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
