defmodule WordGrid do
  defstruct [:grid, :size]

  def initialize(size) do
    grid = for _i <- 1..size do for _j <- 1..size do 'A' end end
    %WordGrid{size: size, grid: grid}
  end

  def toString(grid) do
    Enum.join(
      Enum.map(grid.grid, &Enum.join/1),
      "\n"
    )
  end
end

defmodule MCMC do

  def metroHastings(state, _transition, _proba, _transitionProba, 0) do
    state
  end

  def metroHastings(state, transition, proba, transitionProba, maxStep) do
    newState = transition.(state)
    alpha = proba.(state) * transitionProba.(newState, state) / proba.(newState) * transitionProba.(state, newState)
    u = :rand.uniform()

    nextState = if u < alpha do
      newState
    else
      state
    end

    metroHastings(nextState, transition, proba, transitionProba, maxStep-1)
  end
end

grid = WordGrid.initialize(4)
IO.puts(WordGrid.toString(grid))
