package org.launchcode.controllers;

import org.launchcode.models.*;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.misc.Request;

import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, @RequestParam int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view

        Job job = jobData.findById(id);
        model.addAttribute("job", job);
        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        // validate inputs (model)
        // tell controller to validate it (controller)
        // include error messages in the view

  //      Job newJob = new Job();

//        newJob.setName(jobForm.getName());
//        newJob.setCoreCompetency(jobForm.getCoreCompetencies().get(jobForm.getCoreCompetencyId()));
//        newJob.setEmployer(jobForm.getEmployers().get(jobForm.getEmployerId()));
//        newJob.setLocation(jobForm.getLocations().get(jobForm.getLocationId()));
//        newJob.setPositionType(jobForm.getPositionTypes().get(jobForm.getPositionTypeId()));

        String aName = jobForm.getName();
        Employer aEmployer = jobForm.getEmployers().get(jobForm.getEmployerId());
        Location aLocation = jobForm.getLocations().get(jobForm.getLocationId());
        PositionType aPositionType = jobForm.getPositionTypes().get(jobForm.getPositionTypeId());
        CoreCompetency aSkill = jobForm.getCoreCompetencies().get(jobForm.getCoreCompetencyId());

        Job newJob = new Job(aName, aEmployer, aLocation, aPositionType, aSkill);

        jobData.add(newJob);

        model.addAttribute("job", newJob);

        if (errors.hasErrors()) {
            return "new-job";
        }


        return "redirect:/job?id=" + newJob.getId();

    }
}
