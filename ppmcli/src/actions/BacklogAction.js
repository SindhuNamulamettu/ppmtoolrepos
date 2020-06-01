import axios from "axios";
import { GET_ERRORS } from "./type";
export const addProjectTask = (backlog_id, project_task, history) => async (
  dispatch
) => {
  await axios.post(`/api/backlog/${backlog_id}`, project_task);
  history.push(`/projectBoard/${backlog_id}`);
};
