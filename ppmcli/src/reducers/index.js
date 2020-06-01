import { combineReducers } from "redux";
import errorReducer from "./errorReducer";
import projectReducer from "./projectReducer";
import BacklogReducer from "./BacklogReducer";
export default combineReducers({
  errors: errorReducer,
  projects: projectReducer,
  backlog: BacklogReducer,
});
