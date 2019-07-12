/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.bettem.modules.job.controller;

import com.bettem.common.annotation.SysLog;
import com.bettem.common.utils.PageUtils;
import com.bettem.common.utils.R;
import com.bettem.common.validator.ValidatorUtils;
import com.bettem.modules.job.entity.ScheduleJobEntity;
import com.bettem.modules.job.service.ScheduleJobService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 定时任务
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.2.0 2016-11-28
 */
@RestController
@RequestMapping("/api/sys/schedule/")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping(value = "list",method = RequestMethod.GET)
	@RequiresPermissions("sys:schedule:list")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = scheduleJobService.queryPage(params);
		return R.ok(page);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping(value = "info",method = RequestMethod.GET)
	@RequiresPermissions("sys:schedule:info")
	public R info(@RequestParam String jobId){
		ScheduleJobEntity schedule = scheduleJobService.selectById(jobId);
		return R.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping(value = "save",method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:schedule:save")
	public R save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.save(scheduleJob);
		return R.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping(value = "update",method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
	@RequiresPermissions("sys:schedule:update")
	public R update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		scheduleJobService.update(scheduleJob);
		return R.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping(value = "delete",method = RequestMethod.DELETE)
	@RequiresPermissions("sys:schedule:delete")
	public R delete(@RequestBody String[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		return R.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping(value = "run",method = RequestMethod.PUT)
	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody String[] jobIds){
		scheduleJobService.run(jobIds);
		return R.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping(value = "pause",method = RequestMethod.PUT)
	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody String[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return R.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping(value = "resume",method = RequestMethod.PUT)
	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody String[] jobIds){
		scheduleJobService.resume(jobIds);
		return R.ok();
	}

}
