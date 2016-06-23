package vn.hidalat.utils;

import android.content.Context;

import vn.hidalat.R;
import vn.hidalat.utils.constant.Const;

/**
 * Created by khoavankas on 23/06/2016.
 */
public class Converter {
    public String placeTypeToString(Context context, String type) {
        String res = "";
        switch (type){
            case Const.SIGHTS_PLACE:
                res = context.getResources().getString(R.string.sights);
                break;
            case Const.STAY_PLACE:
                res = context.getResources().getString(R.string.staying);
                break;
            case Const.FOOD_PLACE:
                res = context.getResources().getString(R.string.food);
                break;
            case Const.SHOPPING_PLACE:
                res = context.getResources().getString(R.string.shopping);
                break;
        }
        return res;
    }
}
