package org.sobadfish.placeholdertips.variables;

import cn.nukkit.Player;

import tip.utils.variables.BaseVariable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author Sobadfish
 * @date 2023/10/21
 */
public class PAPITipsVariable extends BaseVariable {


    public PAPITipsVariable(Player player) {
        super(player);
    }

    @Override
    public void strReplace() {

        Class<?> threadClazz = null;
        try {
            threadClazz = Class.forName("com.creeperface.nukkit.placeholderapi.PlaceholderAPIIml");
            Class<?> t2 = Class.forName("com.creeperface.nukkit.placeholderapi.api.scope.GlobalScope");
            Object globalScopeInstance = t2.getField("INSTANCE").get(null);
            Field field = threadClazz.getDeclaredField("instance");
            field.setAccessible(true);
            Object obj = field.get(null);

            Method m2 = threadClazz.getMethod("getPlaceholders",
                    Class.forName("com.creeperface.nukkit.placeholderapi.api.scope.Scope"));
            Map s = (Map) m2.invoke(obj,globalScopeInstance);
            Method contextMethod = t2.getMethod("getDefaultContext");
            Object context = contextMethod.invoke(globalScopeInstance);
            Class<?> placeholderParametersClass = Class.forName("com.creeperface.nukkit.placeholderapi.api.PlaceholderParameters");
            for(Object os: s.keySet()) {
                String kn = os.toString();
                //翻译为值
                Object value = s.get(os);

                try{
                    Method mt = value.getClass().getMethod("getValue",
                            placeholderParametersClass,
                            context.getClass(),
                            Player.class);
                    //PlaceholderParameters

                    Field pf = placeholderParametersClass.getDeclaredField("EMPTY");
                    pf.setAccessible(true);
                    Object placeholderParameters = pf.get(null);
                    String str = mt.invoke(value,placeholderParameters,context,player).toString();
                    addStrReplaceString("{"+kn.replace("%","")+"}", str);
                }catch (Exception e){
                    e.printStackTrace();

                }
            }

        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
