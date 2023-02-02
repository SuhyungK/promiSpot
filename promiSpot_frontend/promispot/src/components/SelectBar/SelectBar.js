import React from "react";
import "./SelectBar.scss";

export default function SelectBar() {
  return (
    <div class="container">
      <div class="tabs">
        <input type="radio" id="radio-1" name="tabs" checked />
        <label class="tab" for="radio-1">
          Upcoming<span class="notification">2</span>
        </label>
        <input type="radio" id="radio-2" name="tabs" />
        <label class="tab" for="radio-2">
          Development
        </label>
        <input type="radio" id="radio-3" name="tabs" />
        <label class="tab" for="radio-3">
          Completed
        </label>
        <span class="glider"></span>
      </div>
    </div>
  );
}
