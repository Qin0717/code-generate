/**
 * @author 张新林1
 * 时间 2017年7月7日下午2:44:42
 * 包名 com.xinhuo.codegenerator.generator
 * 类名 ICallBack
 *
 */
package com.xinlin.code.generate;

import java.util.Map;

public abstract interface ICallBack
{
  public abstract Map<String, Object> execute();

  public abstract Map<String, Object> executeDic();
}
