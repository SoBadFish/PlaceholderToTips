package org.sobadfish.placeholdertips;

import cn.nukkit.plugin.PluginBase;
import org.sobadfish.placeholdertips.variables.PAPITipsVariable;
import tip.utils.Api;
import tip.utils.variables.defaults.DefaultVariables;

/**
 * @author Sobadfish
 * @date 2023/10/21
 */
public class PAPIToTips extends PluginBase {


    @Override
    public void onEnable() {
        this.getLogger().info("Placeholder 转 Tips 变量插件加载中");
        Api.registerVariables("PAPITIPS", PAPITipsVariable.class);
        this.getLogger().info("Placeholder 转 Tips 变量插件加载完成");
    }
}
